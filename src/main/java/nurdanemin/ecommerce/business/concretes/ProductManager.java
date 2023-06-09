package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.BrandService;
import nurdanemin.ecommerce.business.abstracts.CategoryService;
import nurdanemin.ecommerce.business.abstracts.ProductService;
import nurdanemin.ecommerce.business.dto.request.create.product.CreateProductRequest;
import nurdanemin.ecommerce.business.dto.request.update.product.UpdateProductRequest;
import nurdanemin.ecommerce.business.dto.response.create.product.CreateProductResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetProductResponse;
import nurdanemin.ecommerce.business.dto.response.update.product.UpdateProductResponse;
import nurdanemin.ecommerce.business.rules.ProductRules;
import nurdanemin.ecommerce.entities.Category;
import nurdanemin.ecommerce.entities.Product;
import nurdanemin.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductManager  implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper mapper;
    private final ProductRules rules;

    private final CategoryService categoryService;
    private final BrandService brandService;

    @Override
    public List<GetAllProductsResponse> getAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("name")));
        Page<Product> products = repository.findAll(paging);
        return products
                .stream()
                .map(product-> mapper.map(product, GetAllProductsResponse.class))
                .toList();
    }

    @Override
    public GetProductResponse getById(Long id) {
        rules.checkIfProductExistsById(id);
        return mapper.map(repository.findById(id), GetProductResponse.class);
    }

    public List<GetAllProductsResponse> getAllByCategoryName(String categoryName, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.asc("name")));
        List<Product> products = new ArrayList<>();
        for (Product product : repository.findAll(paging)){
            for (Category category :product.getCategories()){
                if (category.getName().equalsIgnoreCase(categoryName)){
                    products.add(product);
                }
            }
        }
        return products
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .toList();
    }

    @Override
    public List<GetAllProductsResponse> getAllByName(String name) {
        return repository.findAllByNameContainingIgnoreCase(name)
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<GetAllProductsResponse> getAllByBrandName(String brandName) {
        List<Product> products = new ArrayList<>();
        for (Product product : repository.findAll()){
            if (product.getBrand().getName().equalsIgnoreCase(brandName)){
                    products.add(product);
            }
        }
        return products
                .stream()
                .map(product -> mapper.map(product, GetAllProductsResponse.class))
                .toList();

    }

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow();
    }


    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        rules.checkIfProductExistsInBrand(request.getName(), request.getBrandId());
        Product product = mapper.map(request, Product.class);
        product.setBrand(brandService.getBrandById(request.getBrandId()));
        product.setCategories(new ArrayList<>());
        if(!request.getCategoryNames().isEmpty()){
            setCategories(product, request.getCategoryNames());}

        product.setId(0L);
        Product createdProd = repository.save(product);
        CreateProductResponse response = mapper.map(createdProd, CreateProductResponse.class);
        response.setCategoryNames(mapProductCategoriesAsNamesList(createdProd));
        response.setBrandName(createdProd.getBrand().getName());

        return response;
    }

    @Override
    public UpdateProductResponse updateProduct(Long id, UpdateProductRequest request) {
        rules.checkIfProductExistsById(id);
        Product product = repository.findById(id).orElseThrow();
        product.setQuantity(request.getQuantity());
        Product createdProd = repository.save(product);
        return mapper.map(createdProd, UpdateProductResponse.class);
    }


    @Override
    public void delete(Long id) {
        rules.checkIfProductExistsById(id);
        repository.deleteById(id);
    }

    public Product  setCategories(Product product, Set<String> categoryNames){
        List<Category> productCategories = product.getCategories();
        for (String categoryName:categoryNames){
            Category category = categoryService.getCategory(categoryName);
            productCategories.add(category);
        }
        return product;
    }

    public void updateProductQuantity(Long productId, int change){
        Product product = repository.findById(productId).orElseThrow();
        product.setQuantity(product.getQuantity() + change);
        repository.save(product);
    }


    public List<String> mapProductCategoriesAsNamesList(Product product){
        List<String> categoryNames = new ArrayList<>();
        for (Category category: product.getCategories()){
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }


}