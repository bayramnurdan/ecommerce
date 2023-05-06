package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
