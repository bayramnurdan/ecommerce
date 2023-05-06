package nurdanemin.ecommerce.business.dto.response.get.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.enums.PaymentStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetPaymentResponse {
    private Long id;
    private String cardHolder;
    private double balance;
    private PaymentStatus status;




}
