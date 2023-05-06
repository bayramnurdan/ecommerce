package nurdanemin.ecommerce.business.dto.response.update.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.enums.PaymentStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePaymentResponse {
    private Long id;
    private String cardHolder;
    private double balance;
    private PaymentStatus status;
}
