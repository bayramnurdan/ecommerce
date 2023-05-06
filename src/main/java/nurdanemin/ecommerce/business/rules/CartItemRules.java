package nurdanemin.ecommerce.business.rules;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemRules {
    private final ProductRepository productRepository;




}
