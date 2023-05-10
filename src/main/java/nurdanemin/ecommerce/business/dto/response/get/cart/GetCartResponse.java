package nurdanemin.ecommerce.business.dto.response.get.cart;

import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.CartItem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
