package nurdanemin.ecommerce.business.dto.request.create.order;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.business.dto.request.create.payment.CreatePaymentRequest;
import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.entities.OrderItem;
import nurdanemin.ecommerce.entities.Payment;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderRequest {
    Long cartId;
    CreatePaymentRequest paymentRequest;
    CreateShippingRequest shippingRequest;



}
