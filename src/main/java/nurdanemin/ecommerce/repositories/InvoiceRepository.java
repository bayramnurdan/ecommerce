package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
