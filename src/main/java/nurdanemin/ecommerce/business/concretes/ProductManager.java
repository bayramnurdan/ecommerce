package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CategoryService;
import nurdanemin.ecommerce.business.abstracts.ProductService;
import nurdanemin.ecommerce.business.dto.request.create.product.CreateProductRequest;
import nurdanemin.ecommerce.business.dto.request.update.product.UpdateProductRequest;
import nurdanemin.ecommerce.business.dto.response.create.product.CreateProductResponse;
import nurdanemin.ecommerce.business.dto.response.get.address.GetAllAddressesResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetProductResponse;
import nurdanemin.ecommerce.business.dto.response.update.product.UpdateProductResponse;
import nurdanemin.ecommerce.business.rules.ProductRules;
import nurdanemin.ecommerce.entities.Category;
import nurdanemin.ecommerce.entities.Product;
import nurdanemin.ecommerce.repositories.CategoryRepository;
import nurdanemin.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProductManager  implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;
    private final ProductRules rules;

    @Override
    public List<GetAllProductsResponse> getAll() {
        List<Product> products = repository.findAll();
        List<GetAllProductsResponse> reponse = products
                .stream()
                .map(product-> mapper.map(product, GetAllProductsResponse.class))
                .toList();
        return reponse;
    }

    @Override
    public GetProductResponse getById(Long id) {
        rules.checkIfProductExistsById(id);
        GetProductResponse response =  mapper.map(repository.findById(id), GetProductResponse.class);
        return response;

    }

    public List<GetAllProductsResponse> getAllByCategoryName(String categoryName) {
        List<Product> products = new ArrayList<>();
        for (Product product : repository.findAll()){
            for (Category category :product.getCategories()){
                if (category.getName().equalsIgnoreCase(categoryName)){
                    products.add(product);
                }
            }
        }
        List<GetAllProductsResponse> response = products
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .toList();
        return response;
    }

    @Override
    public List<GetAllProductsResponse> getAllByBrandName(String brandName) {
        List<Product> products = new ArrayList<>();
        for (Product product : repository.findAll()){
            if (product.getBrand().getName().equalsIgnoreCase(brandName)){
                    products.add(product);
            }
        }
        List<GetAllProductsResponse> response = products
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .toList();
        return response;

    }


    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        rules.checkIfProductExistsInBrand(request.getName(), request.getBrandId());
        Product product = mapper.map(request, Product.class);
        product.setCategories(new HashSet<>());
        if(request.getCategoryNames().size() !=0){
           setCategories(product, request.getCategoryNames());
       }
       product.setId(0L);
       Product createdProd = repository.save(product);
       CreateProductResponse response = mapper.map(createdProd, CreateProductResponse.class);
       response.setCategoryNames(request.getCategoryNames());
       return response;
    }

    @Override
    public UpdateProductResponse updateProduct(Long id, UpdateProductRequest request) {

        // TODO HIÇBİR ŞEKİLDE DOĞRU ÇALIŞMIYOR
        rules.checkIfProductExistsById(id);
        Product product = mapper.map(request, Product.class);
        product.setId(id);
        Product createdProd = repository.save(product);
        UpdateProductResponse response = mapper.map(createdProd, UpdateProductResponse.class);
        return response;
    }


    @Override
    public void delete(Long id) {
        rules.checkIfProductExistsById(id);
        repository.deleteById(id);
    }

    public Product  setCategories(Product product, Set<String> categoryNames){
        Set<Category> productCategories = product.getCategories();
        for (String categoryName:categoryNames){
            Category category = categoryRepository.findByNameIgnoreCase(categoryName);
            productCategories.add(category);
        }
        return product;
    }

    public void updateProductQuantity(Long productId, int change){
        Product product = repository.findById(productId).orElseThrow();
        product.setQuantity(product.getQuantity() + change);
        repository.save(product);
    }
}
