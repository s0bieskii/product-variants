package com.intersport.product.sizecategory;

import com.intersport.product.gender.GenderService;
import com.intersport.product.size.SizeRepository;
import com.intersport.product.sizecategory.dto.SizeCategoryAddDto;
import com.intersport.product.sizecategory.dto.SizeCategoryDto;
import com.intersport.product.sizecategory.dto.SizeCategoryMapper;
import com.intersport.product.sizecategory.dto.SizeCategoryUpdateDto;
import com.intersport.product.utils.exceptions.ResourceExistException;
import com.intersport.product.utils.exceptions.ResourceInUseException;
import com.intersport.product.utils.exceptions.ResourceNotFound;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class SizeCategoryService {

    public static final Logger LOGGER = Logger.getLogger(GenderService.class.getName());
    private final SizeCategoryRepository sizeCategoryRepository;
    private final SizeRepository sizeRepository;
    private final SizeCategoryMapper sizeCategoryMapper;

    public SizeCategoryService(SizeCategoryRepository sizeCategoryRepository,
                               SizeRepository sizeRepository,
                               SizeCategoryMapper sizeCategoryMapper) {
        this.sizeCategoryRepository = sizeCategoryRepository;
        this.sizeRepository = sizeRepository;
        this.sizeCategoryMapper = sizeCategoryMapper;
    }

    @SneakyThrows
    public SizeCategoryDto create(SizeCategoryAddDto sizeCategoryAddDto) {
        LOGGER.info("create " + sizeCategoryAddDto);
        List<SizeCategory> sizeCategoryExist = sizeCategoryRepository.findByNameIgnoreCase(sizeCategoryAddDto.name());
        if (!sizeCategoryExist.isEmpty()) {
            LOGGER.info("Size category with given name already exist NAME" + sizeCategoryAddDto.name());
            throw new ResourceExistException("Size category with given name already exist");
        }
        SizeCategory sizeCategory = sizeCategoryMapper.addDtoToSizeCategory(sizeCategoryAddDto);
        sizeCategory = sizeCategoryRepository.save(sizeCategory);
        LOGGER.info("Size category create success " + sizeCategory);
        return sizeCategoryMapper.sizeCategoryToDto(sizeCategory);
    }

    public List<SizeCategoryDto> getAll() {
        List<SizeCategory> genders = sizeCategoryRepository.findAll();
        LOGGER.info("getAll FOUND: " + genders.size() + "size categories");
        return genders.stream().map(sizeCategoryMapper::sizeCategoryToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public SizeCategoryDto getSizeCategory(Long id) {
        LOGGER.info("getSizeCategory with ID: " + id);
        return sizeCategoryRepository.findById(id).map(sizeCategoryMapper::sizeCategoryToDto)
                .orElseThrow(() -> new ResourceNotFound("Gender with given ID not exist ID: " + id));
    }

    @SneakyThrows
    public SizeCategoryDto updateSizeCategory(SizeCategoryUpdateDto sizeCategoryUpdateDto) {
        if (!sizeCategoryRepository.existsById(sizeCategoryUpdateDto.id())) {
            throw new ResourceNotFound("Size category with given ID not exist ID: " + sizeCategoryUpdateDto.id());
        }
        SizeCategory sizeCategory = sizeCategoryMapper.updateDtoToSize(sizeCategoryUpdateDto);
        sizeCategory = sizeCategoryRepository.save(sizeCategory);
        LOGGER.info("Size category after update: " + sizeCategory);
        return sizeCategoryMapper.sizeCategoryToDto(sizeCategory);
    }

    @SneakyThrows
    public void deleteSizeCategory(Long id) {
        LOGGER.info("Delete size category with ID: " + id);
        if (!sizeCategoryRepository.existsById(id)) {
            LOGGER.warning("Size category with given ID not exist");
            throw new ResourceNotFound("Size category with given ID not exist");
        } else if (!sizeRepository.findBySizeCategoryId(id).isEmpty()) {
            LOGGER.info("Size category is in use");
            throw new ResourceInUseException("Size category is in use");
        }
        LOGGER.info("Size category delete success");
        sizeCategoryRepository.deleteById(id);
    }
}
