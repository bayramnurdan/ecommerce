package nurdanemin.ecommerce.business.dto.request.create.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.common.constants.Regex;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCategoryRequest {
    @NotBlank
    @Pattern(regexp = Regex.properString)
    private String name;
}
