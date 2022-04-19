package com.intersport.product.variant;

import com.intersport.product.variant.dto.VariantAddDto;
import com.intersport.product.variant.dto.VariantDto;
import com.intersport.product.variant.dto.VariantUpdateDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
@RequestMapping("/api/variants")
public class VariantController {

    private final VariantService variantService;

    public VariantController(VariantService variantService) {
        this.variantService = variantService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody VariantAddDto variantAddDto) throws URISyntaxException {
        VariantDto variant = variantService.create(variantAddDto);
        return ResponseEntity.ok(new URI("/api/variants/" + variant.getId()));
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<VariantDto> variants = variantService.getAll();
        return ResponseEntity.ok(variants);
    }

    @GetMapping("/{id}")
    public ResponseEntity getVariant(@PathVariable Long id) {
        VariantDto variant = variantService.getVariant(id);
        return ResponseEntity.ok(variant);
    }

    @PatchMapping()
    public ResponseEntity updateVariant(@RequestBody VariantUpdateDto variantUpdateDto) {
        VariantDto variant = variantService.update(variantUpdateDto);
        return ResponseEntity.ok(variant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVariant(@PathVariable Long id) {
        variantService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
