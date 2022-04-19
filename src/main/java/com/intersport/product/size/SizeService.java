package com.intersport.product.size;

import com.intersport.product.category.Category;
import com.intersport.product.category.CategoryRepository;
import com.intersport.product.category.CategoryService;
import com.intersport.product.size.dto.SizeAddDto;
import com.intersport.product.size.dto.SizeDto;
import com.intersport.product.size.dto.SizeMapper;
import com.intersport.product.size.dto.SizeUpdateDto;
import com.intersport.product.utils.exceptions.ResourceExistException;
import com.intersport.product.utils.exceptions.ResourceInUseException;
import com.intersport.product.utils.exceptions.ResourceNotFound;
import com.intersport.product.variant.VariantRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class SizeService {

    public static final Logger LOGGER = Logger.getLogger(CategoryService.class.getName());
    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;
    private final VariantRepository variantRepository;
    private final CategoryRepository categoryRepository;

    public SizeService(SizeRepository sizeRepository, SizeMapper sizeMapper,
                       VariantRepository variantRepository, CategoryRepository categoryRepository) {
        this.sizeRepository = sizeRepository;
        this.sizeMapper = sizeMapper;
        this.variantRepository = variantRepository;
        this.categoryRepository = categoryRepository;
    }

    @SneakyThrows
    public SizeDto create(SizeAddDto sizeAddDto) {
        LOGGER.info("create " + sizeAddDto);
        Optional<Category> categoryExist = categoryRepository.findById(sizeAddDto.categoryId());
        if (!categoryExist.isPresent()) {
            LOGGER.info("Category with given ID not exist ID: " + sizeAddDto.categoryId());
            throw new ResourceNotFound("Category with given ID not exist ID: " + sizeAddDto.categoryId());
        } else if (sizeRepository.findBySizeAndCategoryId(sizeAddDto.size(), sizeAddDto.categoryId()).isPresent()) {
            LOGGER.info("Size with given name and category exist");
            throw new ResourceExistException("Size with given name and category exist");
        }
        Size size = sizeMapper.addDtoToSize(sizeAddDto);
        size.setCategory(categoryRepository.getById(sizeAddDto.categoryId()));
        size = sizeRepository.save(size);
        LOGGER.info("Size create success " + size);
        return sizeMapper.sizeToDto(size);
    }

    public List<SizeDto> getAll() {
        List<Size> sizes = sizeRepository.findAll();
        LOGGER.info("getAll FOUND: " + sizes.size() + " sizes");
        return sizes.stream().map(sizeMapper::sizeToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public SizeDto getSize(Long id) {

        return sizeRepository.findById(id).map(sizeMapper::sizeToDto)
                .orElseThrow(() -> new ResourceNotFound("Size with given ID not exist ID: " + id));
    }

    @SneakyThrows
    public SizeDto updateSize(SizeUpdateDto sizeUpdateDto) {
        LOGGER.info("updateSize : " + sizeUpdateDto);
        if (!sizeRepository.findById(sizeUpdateDto.id()).isPresent()) {
            LOGGER.info("Size with given ID not exist ID: " + sizeUpdateDto.size());
            throw new ResourceNotFound("Size with given ID not exist ID: " + sizeUpdateDto.size());
        }
        if (!categoryRepository.findById(sizeUpdateDto.categoryId()).isPresent()) {
            LOGGER.info("Category with given ID not exist ID: " + sizeUpdateDto.categoryId());
            throw new ResourceNotFound("Category with given ID not exist ID: " + sizeUpdateDto.id());
        }

        Size size = sizeMapper.updateDtoToSize(sizeUpdateDto);
        size.setCategory(categoryRepository.getById(sizeUpdateDto.categoryId()));
        size = sizeRepository.save(size);
        LOGGER.info("Size update success: " + size);
        return sizeMapper.sizeToDto(size);
    }

    @SneakyThrows
    public void deleteSize(Long id) {
        if (sizeRepository.existsById(id)) {
            LOGGER.warning("Size with given ID not exist");
            throw new ResourceNotFound("Size with given ID not exist");
        } else if (!variantRepository.findBySizeId(id).isEmpty()) {
            LOGGER.info("Size is in use");
            throw new ResourceInUseException("Size is in use");
        }
        LOGGER.info("Size delete success");
        sizeRepository.deleteById(id);
    }
}
