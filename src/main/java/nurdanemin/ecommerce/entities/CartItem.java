package nurdanemin.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="CartItems")
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;
    private Long productId;

    private Long cartId;
    private double price;
    private int quantity;
    private double discount;
    private double totalPrice;


}
