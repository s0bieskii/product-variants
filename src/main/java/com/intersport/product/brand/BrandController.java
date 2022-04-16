package com.intersport.product.brand;

import com.intersport.product.brand.dto.BrandAddDto;
import com.intersport.product.brand.dto.BrandDto;
import com.intersport.product.brand.dto.BrandUpdateDto;
import com.intersport.product.gender.Gender;
import com.intersport.product.gender.dto.GenderAddDto;
import com.intersport.product.gender.dto.GenderUpdateDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity createBrand(@RequestBody BrandAddDto brandAddDto) throws URISyntaxException {
        BrandDto brand = brandService.create(brandAddDto);
        if(brand == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource already exist");
        }
        return ResponseEntity.created(new URI("/api/genders/" + brand.id())).body(brand);
    }

    @GetMapping
    public ResponseEntity getAllBrands() {
        List<BrandDto> brands = brandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBrand(@PathVariable Long id) {
        BrandDto brand = brandService.getBrand(id);
        if (brand == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(brand);
    }

    @PatchMapping
    public ResponseEntity updateBrand(@RequestBody BrandUpdateDto brandUpdateDto) {
        BrandDto brand = brandService.updateBrand(brandUpdateDto);
        if (brand == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(brand);
    }

    @DeleteMapping
    public ResponseEntity deleteBrand(@PathVariable Long id) {
        boolean deleteSuccess = brandService.deleteBrand(id);
        if(!deleteSuccess){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource is in use");
        }
        return ResponseEntity.noContent().build();
    }

}
