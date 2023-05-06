package nurdanemin.ecommerce.business.dto.response.update.order;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.OrderItem;
import nurdanemin.ecommerce.entities.Payment;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrderResponse {
    private Long id;
    private Long userId;


    @ElementCollection
    private Set<Long> orderItems;

    private double totalAmount;
    private double orderDiscount;


    private Long paymentId;
}
