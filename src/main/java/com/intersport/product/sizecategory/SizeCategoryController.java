package com.intersport.product.sizecategory;


import com.intersport.product.sizecategory.dto.SizeCategoryAddDto;
import com.intersport.product.sizecategory.dto.SizeCategoryDto;
import com.intersport.product.sizecategory.dto.SizeCategoryUpdateDto;
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
@RequestMapping("/api/size-category")
public class SizeCategoryController {

    private final SizeCategoryService sizeCategoryService;

    public SizeCategoryController(SizeCategoryService sizeCategoryService) {
        this.sizeCategoryService = sizeCategoryService;
    }

    @PostMapping
    public ResponseEntity createSizeCategory(@RequestBody SizeCategoryAddDto sizeCategoryAddDto) throws URISyntaxException {
        SizeCategoryDto sizeCategory = sizeCategoryService.create(sizeCategoryAddDto);
        return ResponseEntity.created(new URI("/api/size-category/" + sizeCategory.id())).body(sizeCategory);
    }

    @GetMapping
    public ResponseEntity<List<SizeCategoryDto>> getAllSizeCategorys() {
        List<SizeCategoryDto> sizeCategorys = sizeCategoryService.getAll();
        return ResponseEntity.ok(sizeCategorys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeCategoryDto> getSizeCategory(@PathVariable Long id) {
        SizeCategoryDto sizeCategory = sizeCategoryService.getSizeCategory(id);
        return ResponseEntity.ok(sizeCategory);
    }

    @PatchMapping
    public ResponseEntity<SizeCategoryDto> updateSizeCategory(@RequestBody SizeCategoryUpdateDto sizeCategoryUpdateDto) {
        SizeCategoryDto sizeCategoryAfterUpdate = sizeCategoryService.updateSizeCategory(sizeCategoryUpdateDto);
        return ResponseEntity.ok(sizeCategoryAfterUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSizeCategory(@PathVariable Long id) {
        sizeCategoryService.deleteSizeCategory(id);
        return ResponseEntity.noContent().build();
    }
}
