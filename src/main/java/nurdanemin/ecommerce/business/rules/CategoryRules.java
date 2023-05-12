package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.common.constants.Messages;
import nurdanemin.ecommerce.core.exceptions.BusinessException;
import nurdanemin.ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryRules {
    private final CategoryRepository repository;
    public void checkIfCategoryAlreadyExists(String categoryName){
        if (repository.existsByNameIgnoreCase(categoryName)){
            throw new BusinessException(Messages.Category.Exists);
        }
    }

    public void checkIfCategoryExistsById(Long brandId){
        if (!repository.existsById(brandId)){
            throw new BusinessException(Messages.Category.NotExists);
        }
    }
}
