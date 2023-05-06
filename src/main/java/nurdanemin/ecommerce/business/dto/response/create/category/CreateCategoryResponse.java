package nurdanemin.ecommerce.business.dto.response.create.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCategoryResponse {
    private Long id;
    private String name;
}
