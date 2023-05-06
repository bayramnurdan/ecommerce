package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
