package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.*;
import nurdanemin.ecommerce.business.dto.request.create.order.CreateOrderRequest;
import nurdanemin.ecommerce.business.dto.request.update.shipping.UpdateShippingRequest;
import nurdanemin.ecommerce.business.dto.response.create.order.CreateOrderResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetAllOrdersResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetOrderResponse;
import nurdanemin.ecommerce.entities.*;
import nurdanemin.ecommerce.repositories.OrderItemRepository;
import nurdanemin.ecommerce.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository repository;
    private final ModelMapper mapper;

    private final CartService cartService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final InvoiceService invoiceService;
    private final ProductService productService;

    private final OrderItemRepository orderItemRepository;


    @Override
    public List<GetAllOrdersResponse> getAll() {
        List<Order> orders= repository.findAll();
        return orders
                .stream()
                .map(order -> mapper.map(order, GetAllOrdersResponse.class))
                .toList();
    }

    @Override
    public GetOrderResponse getById(Long id) {
        Order order = repository.findById(id).orElseThrow();
        GetOrderResponse response = mapper.map(order, GetOrderResponse.class);
        response.setOrderItemIds(getOrderItemIdsAsList(order));
        return response;
    }


    @Override
    public CreateOrderResponse createOrderForSavedAddress(CreateOrderRequest request) {
        Cart cart = cartService.getCartById(request.getCartId());
        Order order = new Order();
        order.setOrderedAt(LocalDateTime.now());
        order.setUser(cart.getUser());
        List<OrderItem> orderItems = mapCartItemsToOrderItems(cart);
        order.setOrderItems(orderItems);
        order.setTotalAmount(cart.getTotalPrice());

        Payment payment = paymentService.createPayment(request.getPaymentRequest());
        order.setPayment(payment);

        Shipping shipping = shippingService.createShipping(request.getShippingRequest());
        order.setShipping(shipping);

        Order orderCreated = repository.save(order);

        shipping.setOrder(orderCreated);
        shippingService.updateShipping(shipping.getId(), mapper.map(shipping, UpdateShippingRequest.class));

        payment.setOrder(orderCreated);
        paymentService.processPayment(payment);

        Invoice invoice = invoiceService.createInvoice(orderCreated);
        orderCreated.setInvoice(invoice);
        cartService.emptyCart(request.getCartId());
        repository.save(orderCreated);
        setOrderForOrderItems(orderItems, orderCreated);

        CreateOrderResponse response = mapper.map(orderCreated, CreateOrderResponse.class);
        response.setOrderItemIds(getOrderItemIdsAsList(orderCreated));
        return response;
    }

    @Override
    public void delete(Long id) {
        Order order = repository.findById(id).orElseThrow();
        orderItemRepository.deleteAll(order.getOrderItems());
        repository.deleteById(id);
    }


    @Override
    public List<GetAllOrdersResponse> getAllOrdersOfUser(Long userId) {
        List<Order> orders = repository.findAll();
        List<GetAllOrdersResponse> response = new ArrayList<>();
        for(Order order:orders){
            if (order.getUser().getId() == userId){
                GetAllOrdersResponse ordersResponse = mapper.map(order, GetAllOrdersResponse.class);
                response.add(ordersResponse);
            }
        }
        return response;
    }

    public List<Long> getOrderItemIdsAsList(Order order){
        List<Long> orderItemIds= new ArrayList<>();
        for(OrderItem orderItem :order.getOrderItems()){

            orderItemIds.add(orderItem.getId());
        }
        return orderItemIds;
    }

    public void setOrderForOrderItems(List<OrderItem> orderItems, Order orderCreated){
        for (OrderItem orderItem:orderItems){
            orderItem.setOrder(orderCreated);
            orderItemRepository.save(orderItem);
        }

    }

    public List<OrderItem> mapCartItemsToOrderItems(Cart cart){
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = mapper.map(cartItem, OrderItem.class);
            productService.updateProductQuantity(cartItem.getProduct().getId(), -1 * cartItem.getQuantity());
            orderItems.add(orderItem);
        }
        cartService.emptyCart(cart.getId());
        return orderItems;

    }


}