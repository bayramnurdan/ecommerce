package nurdanemin.ecommerce.business.dto.response.get.cart;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.CartItem;
import nurdanemin.ecommerce.entities.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllCartsResponse {
    private Long id;

    private Long userId;
    private double totalPrice;
    @ElementCollection
    Set<Long> cartItems = new HashSet<>();
}
