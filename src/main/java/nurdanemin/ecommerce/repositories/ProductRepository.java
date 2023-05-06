package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long> {
    boolean existsById(Long id);
    boolean existsByNameIgnoreCaseAndBrandId(String productName, Long brandId);



}
