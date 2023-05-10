package nurdanemin.ecommerce.business.abstracts;

import nurdanemin.ecommerce.business.dto.request.create.invoice.CreateInvoiceRequest;
import nurdanemin.ecommerce.business.dto.request.update.invoice.UpdateInvoiceRequest;
import nurdanemin.ecommerce.business.dto.response.create.invoice.CreateInvoiceResponse;
import nurdanemin.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import nurdanemin.ecommerce.business.dto.response.get.GetInvoiceResponse;
import nurdanemin.ecommerce.business.dto.response.update.invoice.UpdateInvoiceResponse;
import nurdanemin.ecommerce.entities.Invoice;
import nurdanemin.ecommerce.entities.Order;

import java.util.List;

public interface InvoiceService {
    List<GetAllInvoicesResponse> getAll();
    GetInvoiceResponse getById(Long id);

    Invoice createInvoice(Order order);
    UpdateInvoiceResponse updateInvoice(Long id, UpdateInvoiceRequest request);
    void delete(Long id);

    void deleteAll();
}
