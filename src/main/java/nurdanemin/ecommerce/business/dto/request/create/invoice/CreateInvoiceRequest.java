package nurdanemin.ecommerce.business.dto.request.create.invoice;

import jakarta.persistence.ElementCollection;

import java.time.LocalDateTime;
import java.util.HashMap;

public class CreateInvoiceRequest {

    private String customerFirstName;
    private String customerLastName;

    @ElementCollection
    private HashMap<String, String> productsBought;
    private double totalAmount;
    private String cardHolder;
    private LocalDateTime orderedAt;
}
