package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.*;
import nurdanemin.ecommerce.business.dto.request.create.cartItem.CreateCartItemRequest;
import nurdanemin.ecommerce.business.dto.request.create.order.CreateOrderRequest;
import nurdanemin.ecommerce.business.dto.request.create.orderItem.CreateOrderItemRequest;
import nurdanemin.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.order.UpdateOrderRequest;
import nurdanemin.ecommerce.business.dto.response.create.cartItem.CreateCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.create.order.CreateOrderResponse;
import nurdanemin.ecommerce.business.dto.response.create.payment.CreatePaymentResponse;
import nurdanemin.ecommerce.business.dto.response.get.cart.GetCartResponse;
import nurdanemin.ecommerce.business.dto.response.get.cartItem.GetCartItemResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetAllOrdersResponse;
import nurdanemin.ecommerce.business.dto.response.get.order.GetOrderResponse;
import nurdanemin.ecommerce.business.dto.response.update.order.UpdateOrderResponse;
import nurdanemin.ecommerce.entities.Cart;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.Order;
import nurdanemin.ecommerce.entities.OrderItem;
import nurdanemin.ecommerce.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private final OrderRepository repository;
    private final ModelMapper mapper;
    private final UserService userService;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final OrderItemService orderItemService;
    private final PaymentService paymentService;
    private  final ShippingService shippingService;
    private final ProductService productService;
    private final InvoiceService invoiceService;
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
        return mapper.map(order, GetOrderResponse.class);
    }

    @Override
    public List<GetOrderResponse> getOrdersByUser(Long userId) {
        return null;
    }

    @Override
    public CreateOrderResponse createOrderForSavedAddress(CreateOrderRequest request) {
        Order order = new Order();
        order.setOrderedAt(LocalDateTime.now());
        CreatePaymentResponse payment = paymentService.createPayment(request.getPaymentRequest());


        GetCartResponse cart = cartService.getById(request.getCartId());

        order.setUserId(userService.findByCartId(request.getCartId()).getId());
        Set<Long> orderItems = new HashSet<>();
        for (Long cartItemId:cart.getCartItems()){
            CartItem cartItemResponse =cartItemService.getById(cartItemId);

            OrderItem orderItem = orderItemService.createOrderItem(cartItemResponse);
            // TODO:  Set order ıD FOR oRDER items

            orderItems.add(orderItem.getId());
            order.setTotalAmount(order.getTotalAmount()+ orderItem.getTotalPrice());
            productService.updateProductQuantity(orderItem.getProductId(), -orderItem.getQuantity() );

        }
        System.out.println(orderItems.stream().toList());
        order.setOrderItems(orderItems);
        order.setPaymentId(payment.getId());

        Order orderCreated = repository.save(order);

        paymentService.processPayment(payment.getId(), cart.getTotalPrice());
        cartService.emptyCart(request.getCartId());
        shippingService.createShipping(request.getShippingRequest(), order.getId());
        invoiceService.createInvoice(orderCreated);



        CreateOrderResponse response = mapper.map(orderCreated, CreateOrderResponse.class);
        response.setTotalAmount(order.getTotalAmount());
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
        repository.deleteById(id);
    }



}
