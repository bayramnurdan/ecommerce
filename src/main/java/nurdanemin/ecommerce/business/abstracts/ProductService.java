package nurdanemin.ecommerce.business.abstracts;


import nurdanemin.ecommerce.business.dto.request.create.product.CreateProductRequest;
import nurdanemin.ecommerce.business.dto.request.update.product.UpdateProductRequest;
import nurdanemin.ecommerce.business.dto.response.create.product.CreateProductResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetProductResponse;
import nurdanemin.ecommerce.business.dto.response.update.product.UpdateProductResponse;
import nurdanemin.ecommerce.entities.Product;

import java.util.List;

public interface ProductService {
    List<GetAllProductsResponse> getAll();
    GetProductResponse getById(Long id);
    List<GetAllProductsResponse> getAllByCategoryName(String categoryName);
    List<GetAllProductsResponse> getAllByBrandName(String brandName);
    Product getProductbyId(Long id);

    CreateProductResponse createProduct(CreateProductRequest request);
    UpdateProductResponse updateProduct(Long id, UpdateProductRequest request);
    void delete(Long id);
    void updateProductQuantity(Long productId, int amount);


}
