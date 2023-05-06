package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
