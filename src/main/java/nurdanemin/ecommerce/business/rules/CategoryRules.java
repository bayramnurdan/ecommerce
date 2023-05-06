package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryRules {
    private final CategoryRepository repository;
    public void checkIfCategoryAlreadyExists(String categoryName){
        if (repository.existsByNameIgnoreCase(categoryName)){
            // TODO : Business exception
            throw new RuntimeException("CATEGORY_ALREADY_EXISTS");
        }
    }

    public void checkIfCategoryExistsById(Long brandId){
        if (!repository.existsById(brandId)){
            // TODO : Business exception
            throw new RuntimeException("THERE_IS_NO_SUCH_CATEGORY");
        }
    }
}
