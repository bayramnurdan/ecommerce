package nurdanemin.ecommerce.business.dto.response.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllInvoicesResponse {
    private String customerFirstName;
    private String customerLastName;

    private LocalDateTime OrderedAt;
    private double totalAmount;
    private String cardHolder;


}