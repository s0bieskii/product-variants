package com.intersport.product.category;

import com.intersport.product.category.dto.CategoryAddDto;
import com.intersport.product.category.dto.CategoryUpdateDto;
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
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity createCategory(@RequestBody CategoryAddDto categoryAddDto) {
        Category category = categoryService.create(categoryAddDto);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category already exist");
        }
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity getAllCategories() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategory(@PathVariable Long id) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PatchMapping
    public ResponseEntity updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryService.update(categoryUpdateDto);
        if (category == null) {
            return ResponseEntity.badRequest().body("Category not exist!");
        }
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        boolean deleteSuccess = categoryService.delete(id);
        if (!deleteSuccess) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource in use");
        }
        return ResponseEntity.noContent().build();
    }
}
