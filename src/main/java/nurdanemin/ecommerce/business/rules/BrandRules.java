package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.repositories.BrandRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandRules {
    private final BrandRepository repository;

    public void checkIfBrandAlreadyExists(String brandName){
        if (repository.existsByNameIgnoreCase(brandName)){
            throw new RuntimeException("BRAND_ALREADY_EXISTS");
        }
    }

    public void checkIfBrandExistsById(Long brandId){
        if (!repository.existsById(brandId)){
            throw new RuntimeException("THERE_IS_NO_SUCH_BRAND");
        }
    }
}
