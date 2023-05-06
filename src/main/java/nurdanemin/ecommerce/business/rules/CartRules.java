package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.repositories.CartRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartRules {
    private final CartRepository repository;
    public void checkIfCartExistsById(Long id){

    }
}
