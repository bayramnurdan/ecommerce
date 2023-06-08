package nurdanemin.ecommerce.business.dto.response.get.cart;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCartResponse {
    private Long id;

    private Long userId;
    private double totalPrice;
    @ElementCollection
    Set<Long> cartItemIds;
}