package nurdanemin.ecommerce.business.dto.response.get.order;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetOrderResponse {
    private Long id;
    private Long userId;


    @ElementCollection
    private List<Long> orderItemIds = new ArrayList<>();

    private double totalAmount;
    private double orderDiscount;


    private Long paymentId;
    private Long shippingId;
    private Long invoiceId;
}