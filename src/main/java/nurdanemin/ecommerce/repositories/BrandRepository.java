package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository  extends JpaRepository<Brand, Long> {
    boolean existsByNameIgnoreCase(String brandName);
    boolean existsById(Long id);
    Brand findBrandByNameIgnoreCase(String brandName);

}
