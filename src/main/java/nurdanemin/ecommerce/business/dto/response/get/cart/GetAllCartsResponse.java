package nurdanemin.ecommerce.business.dto.response.get.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllCartsResponse {
    private Long id;

    private Long userId;
    private double totalPrice;
    //List<String> productNames ; streamda bir şeyler yapmalı
}