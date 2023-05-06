package nurdanemin.ecommerce.business.dto.request.create.brand;

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
public class CreateBrandRequest {
    private String name;

}
