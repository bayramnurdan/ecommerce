package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.*;
import nurdanemin.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import nurdanemin.ecommerce.business.dto.response.get.GetInvoiceResponse;
import nurdanemin.ecommerce.entities.Invoice;
import nurdanemin.ecommerce.entities.Order;
import nurdanemin.ecommerce.repositories.InvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> response = invoices
                .stream()
                .map(invoice -> mapper.map(invoice, GetAllInvoicesResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetInvoiceResponse getById(Long id) {
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.map(invoice, GetInvoiceResponse.class);
        return response;
    }

    @Override
    public Invoice createInvoice(Order order ) {
        Invoice invoice = new Invoice();
        invoice.setCustomerFirstName(order.getUser().getFirstName());
        invoice.setCustomerLastName(order.getUser().getLastName());
        invoice.setOrderedAt(order.getOrderedAt());
        invoice.setTotalAmount(order.getTotalAmount());
        invoice.setCardHolder(order.getPayment().getCardHolder());
        invoice.setOrder(order);
        Invoice invoiceCreated = repository.save(invoice);
        return invoiceCreated;
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);


    }

}