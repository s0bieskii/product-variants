package com.intersport.product.product;

import com.intersport.product.product.dto.ProductAddDto;
import com.intersport.product.product.dto.ProductDto;
import com.intersport.product.product.mapper.ProductMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public Product createProduct(ProductAddDto productToAdd) {
        return productRepository.save(productMapper.addDtoToProduct(productToAdd));
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(productMapper::productToDto).collect(Collectors.toList());
    }

    public ProductDto getProduct(Long id) {
        return productRepository.findById(id).map(productMapper::productToDto).orElse(null);
    }

    public Optional<ProductDto> update(Long id) {
        return null;
    }

    public boolean delete(Long id) {
        if(productRepository.existsById(id)){
            return false;
        }
        productRepository.delete(productRepository.getById(id));
        return true;
    }


}
