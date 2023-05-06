package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
