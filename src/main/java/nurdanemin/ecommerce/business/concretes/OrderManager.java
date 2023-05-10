package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.*;
import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.request.create.order.CreateOrderRequest;
import nurdanemin.ecommerce.business.dto.request.create.orderItem.CreateOrderItemRequest;
import nurdanemin.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.order.UpdateOrderRequest;
import nurdanemin.ecommerce.business.dto.request.update.payment.UpdatePaymentRequest;
import nurdanemin.ecommerce.business.dto.request.update.shipping.UpdateShippingRequest;
import nurdanemin.ecommerce.business.dto.response.create.cartItem.CreateCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.create.order.CreateOrderResponse;
import nurdanemin.ecommerce.business.dto.response.create.payment.CreatePaymentResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetCartResponse;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetAllOrdersResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetOrderResponse;
import nurdanemin.ecommerce.business.dto.response.update.order.UpdateOrderResponse;
import nurdanemin.ecommerce.entities.*;
import nurdanemin.ecommerce.entities.enums.PaymentStatus;
import nurdanemin.ecommerce.repositories.CartItemRepository;
import nurdanemin.ecommerce.repositories.OrderItemRepository;
import nurdanemin.ecommerce.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository repository;
    private final ModelMapper mapper;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final InvoiceService invoiceService;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    @Override
    public List<GetAllOrdersResponse> getAll() {
        List<Order> orders= repository.findAll();
        List<GetAllOrdersResponse> response = orders
                .stream()
                .map(order -> mapper.map(order, GetAllOrdersResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetOrderResponse getById(Long id) {
        Order order = repository.findById(id).orElseThrow();
        GetOrderResponse response = mapper.map(order, GetOrderResponse.class);
        response.setOrderItemIds(getOrderItemIdsAsList(order));
        return response;
    }

    @Override
    public List<GetOrderResponse> getOrdersByUser(Long userId) {
        return null;
    }

    @Override
    public CreateOrderResponse createOrderForSavedAddress(CreateOrderRequest request) {
        Cart cart = cartService.getCartById(request.getCartId());
        Order order = new Order();
        order.setOrderedAt(LocalDateTime.now());
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setDiscount(cartItem.getDiscount());
            orderItemRepository.save(orderItem);
            productService.updateProductQuantity(cartItem.getProduct().getId(), -1 * cartItem.getQuantity());
            cartItem.setCart(null);

            cartItemRepository.delete(cartItem);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTotalAmount(cart.getTotalPrice());

        Payment payment = paymentService.createPayment(request.getPaymentRequest());
        order.setPayment(payment);


        Shipping shipping = shippingService.createShipping(request.getShippingRequest());
        order.setShipping(shipping);

        order.setUser(cart.getUser());


        Order orderCreated = repository.save(order);


        shipping.setOrder(orderCreated);
        shippingService.updateShipping(shipping.getId(), mapper.map(shipping, UpdateShippingRequest.class));

        payment.setOrder(orderCreated);
        paymentService.processPayment(payment);

        Invoice invoice = invoiceService.createInvoice(orderCreated);
        orderCreated.setInvoice(invoice);
        cartService.emptyCart(request.getCartId());
        repository.save(orderCreated);



        for (OrderItem orderItem:orderItems){
            orderItem.setOrder(orderCreated);
            orderItemRepository.save(orderItem);
        }



       CreateOrderResponse response = mapper.map(orderCreated, CreateOrderResponse.class);
        response.setOrderItemIds(getOrderItemIdsAsList(orderCreated));
        return response;
    }


    @Override
    public UpdateOrderResponse updateOrder(Long id, UpdateOrderRequest request) {
        Order order = mapper.map(request, Order.class);
        order.setId(id);
        repository.save(order);
        UpdateOrderResponse response = mapper.map(order, UpdateOrderResponse.class);
        return response;
    }



    @Override
    public void delete(Long id) {
        Order order = repository.findById(id).orElseThrow();
        for (OrderItem orderItem:order.getOrderItems()){
            orderItemRepository.delete(orderItem);
        }

        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
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


}
