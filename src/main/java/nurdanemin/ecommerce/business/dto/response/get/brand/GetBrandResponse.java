package nurdanemin.ecommerce.business.dto.response.get.brand;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Product;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBrandResponse {
    private Long id;

    private String name;

    @OneToMany(mappedBy = "brand")
    private Set<Product> products;
}
