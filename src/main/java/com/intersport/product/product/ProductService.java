package com.intersport.product.product;

import com.intersport.product.brand.BrandRepository;
import com.intersport.product.category.CategoryRepository;
import com.intersport.product.gender.GenderRepository;
import com.intersport.product.model.Model;
import com.intersport.product.model.ModelRepository;
import com.intersport.product.product.dto.ProductAddDto;
import com.intersport.product.product.dto.ProductDto;
import com.intersport.product.product.dto.ProductMapper;
import com.intersport.product.product.dto.ProductUpdateDto;
import com.intersport.product.product.dto.ProductWithModelAddDto;
import com.intersport.product.type.TypeRepository;
import com.intersport.product.utils.exceptions.ResourceExistException;
import com.intersport.product.utils.exceptions.ResourceNotFound;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public static final Logger LOGGER = Logger.getLogger(ProductService.class.getName());
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;
    private final GenderRepository genderRepository;
    private final TypeRepository typeRepository;
    private final ModelRepository modelRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper, BrandRepository brandRepository,
                          GenderRepository genderRepository, TypeRepository typeRepository,
                          ModelRepository modelRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.brandRepository = brandRepository;
        this.genderRepository = genderRepository;
        this.typeRepository = typeRepository;
        this.modelRepository = modelRepository;
        this.categoryRepository = categoryRepository;
    }

    @SneakyThrows
    public Product createProductWithModel(ProductWithModelAddDto productToAdd) {
        LOGGER.info("create " + productToAdd);
        if (!brandRepository.existsById(productToAdd.brandId())
                || !categoryRepository.existsById(productToAdd.categoryId())) {
            LOGGER.info("Nested resource not found");
            throw new ResourceNotFound("Nested resource not found");
        } else if (!genderRepository.existsById(productToAdd.model().getGenderId()) ||
                !typeRepository.existsById(productToAdd.model().getTypeId())) {
            LOGGER.info("Nested resource in Model not found");
            throw new ResourceNotFound("Nested resource in Model not found");
        }
        Product product = productMapper.addDtoWithModelToProduct(productToAdd);
        product.setBrand(brandRepository.getById(productToAdd.brandId()));
        product.setCategory(categoryRepository.getById(productToAdd.categoryId()));
        Model model = product.getModel();
        model.setGender(genderRepository.getById(productToAdd.model().getGenderId()));
        model.setType(typeRepository.getById(productToAdd.model().getTypeId()));
        product.setModel(model);
        LOGGER.info("Size create success " + product);
        return productRepository.save(product);
    }

    @SneakyThrows
    public Product createProduct(ProductAddDto productToAdd) {
        LOGGER.info("create " + productToAdd);
        if (!brandRepository.existsById(productToAdd.brandId()) || !modelRepository.existsById(productToAdd.modelId())
                || categoryRepository.existsById(productToAdd.categoryId())) {
            LOGGER.info("Nested resource not found");
            throw new ResourceNotFound("Nested resource not found");
        }
        Product product = productMapper.addDtoToProduct(productToAdd);
        product.setBrand(brandRepository.getById(productToAdd.brandId()));
        product.setModel(modelRepository.getById(productToAdd.modelId()));
        product.setCategory(categoryRepository.getById(productToAdd.categoryId()));
        LOGGER.info("Size create success " + product);
        return productRepository.save(product);
    }

    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        LOGGER.info("getAll FOUND: " + products.size() + " products");
        return products.stream().map(productMapper::productToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public ProductDto getProduct(Long id) {
        LOGGER.info("getProduct ID: " + id);
        return productRepository.findById(id).map(productMapper::productToDto)
                .orElseThrow(() -> new ResourceNotFound("Product with given ID not exist ID: " + id));
    }


    @SneakyThrows
    public ProductDto update(ProductUpdateDto productToUpdate) {
        LOGGER.info("updateProduct : " + productToUpdate);
        if (!productRepository.existsById(productToUpdate.id())) {
            LOGGER.info("Product with given ID not exist ID: " + productToUpdate.id());
            throw new ResourceNotFound("Product with given ID not exist ID: " + productToUpdate.id());
        } else if (!brandRepository.existsById(productToUpdate.brandId()) ||
                !modelRepository.existsById(productToUpdate.modelId())
                || !categoryRepository.existsById(productToUpdate.categoryId())) {
            LOGGER.info("Nested resource not found");
            throw new ResourceExistException("Nested resource not found");
        }
        Product product = productMapper.productUpdate(productToUpdate);
        product.setModel(modelRepository.getById(productToUpdate.modelId()));
        product.setBrand(brandRepository.getById(productToUpdate.brandId()));
        product.setCategory(categoryRepository.getById(productToUpdate.categoryId()));
        product = productRepository.save(product);
        LOGGER.info("Product update success :" + product);
        return productMapper.productToDto(product);
    }

    @SneakyThrows
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            LOGGER.info("Product with given ID not exist ID: " + id);
            throw new ResourceNotFound("Product with given ID not exist ID: " + id);
        }
        LOGGER.info("Delete success");
        productRepository.delete(productRepository.getById(id));
    }


}
