package nurdanemin.ecommerce.business.dto.response.update.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCategoryResponse {
    private Long id;

    private String name;
}
