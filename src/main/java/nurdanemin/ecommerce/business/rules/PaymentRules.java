package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.common.constants.Messages;
import nurdanemin.ecommerce.core.exceptions.BusinessException;
import nurdanemin.ecommerce.entities.Payment;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentRules {
    public void checkIfBalanceIsEnough(Payment payment){
        if (payment.getBalance()< payment.getOrder().getTotalAmount()){
            throw new BusinessException(Messages.Payment.NotEnough);
        }
    }
}
