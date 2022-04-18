package com.intersport.product.product;

import com.intersport.product.gender.GenderRepository;
import com.intersport.product.brand.BrandRepository;
import com.intersport.product.product.dto.ProductAddDto;
import com.intersport.product.product.dto.ProductDto;
import com.intersport.product.product.dto.ProductMapper;
import com.intersport.product.product.dto.ProductUpdateDto;
import com.intersport.product.type.TypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final BrandRepository brandRepository;
    private final GenderRepository genderRepository;
    private final TypeRepository typeRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper,
                          BrandRepository brandRepository, GenderRepository genderRepository,
                          TypeRepository typeRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.brandRepository = brandRepository;
        this.genderRepository = genderRepository;
        this.typeRepository = typeRepository;

    }

    //TODO create validation for allFields
    public Product createProduct(ProductAddDto productToAdd) {
        Product product = productMapper.addDtoToProduct(productToAdd);
        product.setBrand(brandRepository.findById(productToAdd.brandId()).get());
        product.getModel().setGender(genderRepository.findById(productToAdd.model().getGenderId()).get());
        product.getModel().setType(typeRepository.findById(productToAdd.model().getTypeId()).get());
        return productRepository.save(product);
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(productMapper::productToDto).collect(Collectors.toList());
    }

    public ProductDto getProduct(Long id) {
        return productRepository.findById(id).map(productMapper::productToDto).orElse(null);
    }

    public ProductDto update(ProductUpdateDto productToUpdate) {
        if (!productRepository.existsById(productToUpdate.getId())) {
            return null;
        }
        Product productDb = productRepository.getById(productToUpdate.getId());
        Product product = productMapper.productUpdate(productToUpdate);
        product.setBrand(productDb.getBrand());
        product.setModel(productDb.getModel());
        Product savedProduct = productRepository.save(product);
        return productMapper.productToDto(savedProduct);
    }

    public boolean delete(Long id) {
        if (productRepository.existsById(id)) {
            return false;
        }
        productRepository.delete(productRepository.getById(id));
        return true;
    }


}
