package nurdanemin.ecommerce.business.abstracts;

import nurdanemin.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import nurdanemin.ecommerce.business.dto.request.update.payment.UpdatePaymentRequest;
import nurdanemin.ecommerce.business.dto.response.create.payment.CreatePaymentResponse;
import nurdanemin.ecommerce.business.dto.response.get.payment.GetAllPaymentsResponse;
import nurdanemin.ecommerce.business.dto.response.get.payment.GetPaymentResponse;
import nurdanemin.ecommerce.business.dto.response.update.payment.UpdatePaymentResponse;
import nurdanemin.ecommerce.entities.Payment;

import java.util.List;

public interface PaymentService {
    List<GetAllPaymentsResponse> getAll();
    GetPaymentResponse getById(Long id);
    Payment createPayment(CreatePaymentRequest request);
    UpdatePaymentResponse updatePayment(Long id, UpdatePaymentRequest request);
    void delete(Long id);
    void processPayment(Payment payment);
}
