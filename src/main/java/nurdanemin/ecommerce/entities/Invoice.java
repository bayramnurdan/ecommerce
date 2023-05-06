package nurdanemin.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerFirstName;
    private String customerLastName;

    @ElementCollection
    private HashMap<String, String> productsBought;
    private double totalAmount;
    private String cardHolder;
    private LocalDateTime orderedAt;



}
