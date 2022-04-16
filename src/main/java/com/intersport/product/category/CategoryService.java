package com.intersport.product.category;

import com.intersport.product.category.dto.CategoryAddDto;
import com.intersport.product.category.dto.CategoryUpdateDto;
import com.intersport.product.product.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper,
                           ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
    }

    public Category create(CategoryAddDto categoryAddDto) {
        if(categoryRepository.findCategoryByName(categoryAddDto.name()).isPresent()){
            return null;
        }
        Category category = categoryMapper.addDtoToCategory(categoryAddDto);
        return categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category update(CategoryUpdateDto categoryUpdateDto) {
        if(!categoryRepository.existsById(categoryUpdateDto.id())){
            return null;
        }
        Category category = categoryMapper.updateDtoToCategory(categoryUpdateDto);
        return categoryRepository.save(category);
    }

    //TODO validation for categoryExist
    public boolean delete(Long id) {
        if(productRepository.findByCategoryId(id).isPresent()){
            return false;
        }
        productRepository.deleteById(id);
        return true;

    }
}
