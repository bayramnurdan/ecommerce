package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.PaymentService;
import nurdanemin.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import nurdanemin.ecommerce.business.dto.request.update.payment.UpdatePaymentRequest;
import nurdanemin.ecommerce.business.dto.response.create.payment.CreatePaymentResponse;
import nurdanemin.ecommerce.business.dto.response.get.payment.GetAllPaymentsResponse;
import nurdanemin.ecommerce.business.dto.response.get.payment.GetPaymentResponse;
import nurdanemin.ecommerce.business.dto.response.update.payment.UpdatePaymentResponse;
import nurdanemin.ecommerce.entities.Payment;
import nurdanemin.ecommerce.entities.enums.PaymentStatus;
import nurdanemin.ecommerce.repositories.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {
    private final ModelMapper mapper;
    private final PaymentRepository repository;
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        return null;
    }

    @Override
    public GetPaymentResponse getById(Long id) {
        Payment payment = repository.findById(id).orElseThrow();
        GetPaymentResponse response = mapper.map(payment, GetPaymentResponse.class);
        return response;
    }

    @Override
    public CreatePaymentResponse createPayment(CreatePaymentRequest request) {
        Payment payment = mapper.map(request, Payment.class);
        payment.setStatus(PaymentStatus.FAILED);
        repository.save(payment);
        return mapper.map(payment, CreatePaymentResponse.class);

    }

    @Override
    public UpdatePaymentResponse updatePayment(Long id, UpdatePaymentRequest request) {
        return null;
    }





    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void processPayment(Long paymentId, double amount) {
        Payment payment = repository.findById(paymentId).orElseThrow();
        payment.setBalance(payment.getBalance()-amount);
        payment.setStatus(PaymentStatus.SUCCESS);
        repository.save(payment);

    }
}
