package com.intersport.product.product;

import com.intersport.product.product.dto.ProductAddDto;
import com.intersport.product.product.dto.ProductDto;
import com.intersport.product.product.dto.ProductUpdateDto;
import com.intersport.product.product.dto.ProductWithModelAddDto;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity createProductWithModel(@RequestBody ProductWithModelAddDto productToAdd)
            throws URISyntaxException {
        Product savedProduct = productService.createProductWithModel(productToAdd);
        return ResponseEntity.created(new URI("/api/products/" + savedProduct.getId())).build();
    }

    @PostMapping("/add")
    public ResponseEntity createProduct(@RequestBody ProductAddDto productToAdd) throws URISyntaxException {
        Product savedProduct = productService.createProduct(productToAdd);
        return ResponseEntity.created(new URI("/api/products/" + savedProduct.getId())).build();
    }

    @GetMapping("/")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        ProductDto product = productService.getProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PatchMapping()
    public ResponseEntity updateProduct(@RequestBody ProductUpdateDto productToUpdate) {
        ProductDto updatedProduct = productService.update(productToUpdate);
        if (updatedProduct == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
