package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductRules {
    private final ProductRepository repository;

    public void checkIfProductExistsInBrand(String productName, Long brandId){
        if (repository.existsByNameIgnoreCaseAndBrandId(productName, brandId)){
            throw new RuntimeException("PRODUCT_ALREADY_EXISTS");
        }
    }

    public void checkIfProductExistsById(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("PRODUCT_DOES_NOT_EXISTS");
        }
    }

}
