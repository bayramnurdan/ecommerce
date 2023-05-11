package nurdanemin.ecommerce.business.dto.response.get.product;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Brand;
import nurdanemin.ecommerce.entities.Category;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllProductsResponse {
    private Long id;
    private String name;


    private String brandName;

    private Long quantity;
    private double price;
    private double discount;

}
