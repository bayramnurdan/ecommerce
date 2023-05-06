package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
