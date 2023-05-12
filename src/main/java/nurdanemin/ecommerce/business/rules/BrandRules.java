package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.common.constants.Messages;
import nurdanemin.ecommerce.core.exceptions.BusinessException;
import nurdanemin.ecommerce.repositories.BrandRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandRules {
    private final BrandRepository repository;

    public void checkIfBrandAlreadyExists(String brandName){
        if (repository.existsByNameIgnoreCase(brandName)){
            throw new BusinessException(Messages.Brand.Exists);
        }
    }

    public void checkIfBrandExistsById(Long brandId){
        if (!repository.existsById(brandId)){
            throw new BusinessException(Messages.Brand.NotExists);
        }
    }
}
