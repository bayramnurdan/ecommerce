package nurdanemin.ecommerce.repositories;

import nurdanemin.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String categoryName);
    boolean existsById(Long id);
    Category findByNameIgnoreCase(String name);
}
