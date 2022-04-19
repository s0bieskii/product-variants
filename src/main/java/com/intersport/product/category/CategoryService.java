package com.intersport.product.category;

import com.intersport.product.category.dto.CategoryAddDto;
import com.intersport.product.category.dto.CategoryDto;
import com.intersport.product.category.dto.CategoryMapper;
import com.intersport.product.category.dto.CategoryUpdateDto;
import com.intersport.product.product.ProductRepository;
import com.intersport.product.size.SizeRepository;
import com.intersport.product.utils.exceptions.ResourceExistException;
import com.intersport.product.utils.exceptions.ResourceInUseException;
import com.intersport.product.utils.exceptions.ResourceNotFound;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    public static final Logger LOGGER = Logger.getLogger(CategoryService.class.getName());
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper,
                           ProductRepository productRepository, SizeRepository sizeRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
        this.sizeRepository = sizeRepository;
    }

    @SneakyThrows
    public CategoryDto create(CategoryAddDto categoryAddDto) {
        LOGGER.info("create " + categoryAddDto);
        Optional<Category> categoryExist = categoryRepository.findByNameIgnoreCase(categoryAddDto.name());
        if (categoryExist.isPresent()) {
            LOGGER.info("Category with given name already exist NAME" + categoryAddDto.name());
            throw new ResourceExistException("Category with given name already exist");
        }
        Category category = categoryMapper.addDtoToCategory(categoryAddDto);
        category = categoryRepository.save(category);
        LOGGER.info("Category create success " + category);
        return categoryMapper.categoryToDto(category);
    }

    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepository.findAll();
        LOGGER.info("getAll FOUND: " + categories.size() + "categories");
        return categories.stream().map(categoryMapper::categoryToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public CategoryDto getCategory(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::categoryToDto)
                .orElseThrow(() -> new ResourceNotFound("Category with given ID not exist ID: " + id));
    }

    @SneakyThrows
    public CategoryDto update(CategoryUpdateDto categoryUpdateDto) {
        if (!categoryRepository.existsById(categoryUpdateDto.id())) {
            throw new ResourceNotFound("Category with given ID not exist ID: " + categoryUpdateDto.id());
        }
        Category category = categoryMapper.updateDtoToCategory(categoryUpdateDto);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToDto(category);
    }

    @SneakyThrows
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            LOGGER.warning("Category with given ID not exist");
            throw new ResourceNotFound("Category with given ID not exist");
        } else if (!productRepository.findByCategoryId(id).isEmpty() ||
                !sizeRepository.findBySizeCategoryId(id).isEmpty()) {
            LOGGER.info("Category is in use");
            throw new ResourceInUseException("Category is in use");
        }
        LOGGER.info("Category delete success");
        categoryRepository.deleteById(id);
    }
}
