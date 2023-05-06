package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.*;
import nurdanemin.ecommerce.business.dto.request.update.invoice.UpdateInvoiceRequest;
import nurdanemin.ecommerce.business.dto.response.create.invoice.CreateInvoiceResponse;
import nurdanemin.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import nurdanemin.ecommerce.business.dto.response.get.GetInvoiceResponse;
import nurdanemin.ecommerce.business.dto.response.get.orderItem.GetOrderItemResponse;
import nurdanemin.ecommerce.business.dto.response.update.invoice.UpdateInvoiceResponse;
import nurdanemin.ecommerce.entities.Invoice;
import nurdanemin.ecommerce.entities.Order;
import nurdanemin.ecommerce.repositories.InvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final UserService userService;
    private final PaymentService paymentService;
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final ModelMapper mapper;
    @Override
    public List<GetAllInvoicesResponse> getAll() {
        return null;
    }

    @Override
    public GetInvoiceResponse getById(Long id) {
        return null;
    }

    @Override
    public CreateInvoiceResponse createInvoice(Order order ) {

        Invoice invoice = new Invoice();
        invoice.setCustomerFirstName(userService.getById(order.getUserId()).getFirstName());
        invoice.setCustomerLastName(userService.getById(order.getUserId()).getLastName());
        invoice.setOrderedAt(order.getOrderedAt());
        invoice.setTotalAmount(order.getTotalAmount());
        invoice.setCardHolder(paymentService.getById(order.getPaymentId()).getCardHolder());
        HashMap<String, String > productsPurchased = new HashMap<>();

        for (Long orderItemId: order.getOrderItems()){
            GetOrderItemResponse orderItem = orderItemService.getById(orderItemId);
            Long productId =orderItem.getProductId(); // SORUN  BURDA
            int quantity = orderItem.getQuantity();
            double price = orderItem.getPrice();
            productsPurchased.put(productService.getById(productId).getName(),
                    "Integer.toString(quantity) * Double.toString(price)" );

        }
        repository.save(invoice);
        CreateInvoiceResponse response = mapper.map(invoice, CreateInvoiceResponse.class);
        return response;

    }

    @Override
    public UpdateInvoiceResponse updateInvoice(Long id, UpdateInvoiceRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
