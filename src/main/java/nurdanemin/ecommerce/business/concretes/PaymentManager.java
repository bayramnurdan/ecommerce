package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.PaymentService;
import nurdanemin.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import nurdanemin.ecommerce.business.dto.response.get.payment.GetAllPaymentsResponse;
import nurdanemin.ecommerce.business.dto.response.get.payment.GetPaymentResponse;
import nurdanemin.ecommerce.business.rules.PaymentRules;
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
    private final PaymentRules rules;
    @Override
    public List<GetAllPaymentsResponse> getAll() {
        return repository.findAll().stream()
                .map(payment -> mapper.map(payment, GetAllPaymentsResponse.class))
                .toList();
    }

    @Override
    public GetPaymentResponse getById(Long id) {
        Payment payment = repository.findById(id).orElseThrow();
        return mapper.map(payment, GetPaymentResponse.class);
    }

    @Override
    public Payment createPayment(CreatePaymentRequest request) {
        Payment payment = mapper.map(request, Payment.class);
        payment.setStatus(PaymentStatus.FAILED);
        return repository.save(payment);

    }
    public void processPayment(Payment payment){
        rules.checkIfBalanceIsEnough(payment);
        double newBalance = payment.getBalance()-payment.getOrder().getTotalAmount();
        payment.setBalance(newBalance);
        payment.setStatus(PaymentStatus.SUCCESS);
        repository.save(payment);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

}