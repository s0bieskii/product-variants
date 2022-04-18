package com.intersport.product.brand;

import com.intersport.product.brand.dto.BrandAddDto;
import com.intersport.product.brand.dto.BrandDto;
import com.intersport.product.brand.dto.BrandMapper;
import com.intersport.product.brand.dto.BrandUpdateDto;
import com.intersport.product.category.CategoryService;
import com.intersport.product.product.ProductRepository;
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
public class BrandService {

    public static final Logger LOGGER = Logger.getLogger(CategoryService.class.getName());
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final ProductRepository productRepository;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper, ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
        this.productRepository = productRepository;
    }

    @SneakyThrows
    public BrandDto create(BrandAddDto brandAddDto) {
        LOGGER.info("create " + brandAddDto);
        Optional<Brand> brandExist = brandRepository.findBrandByName(brandAddDto.name());
        if (brandExist.isPresent()) {
            LOGGER.info("Brand with given name already exist NAME" + brandAddDto.name());
            throw new ResourceExistException("Brand with given name already exist");
        }
        Brand brand = brandMapper.addDtoToBrand(brandAddDto);
        Brand savedBrand = brandRepository.save(brand);
        LOGGER.info("Category create success " + savedBrand);
        return brandMapper.brandToDto(savedBrand);
    }


    public List<BrandDto> getAll() {
        List<Brand> brands = brandRepository.findAll();
        LOGGER.info("getAll FOUND: " + brands.size() + "brands");
        return brands.stream().map(brandMapper::brandToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public BrandDto getBrand(Long id) {

        return brandRepository.findById(id).map(brandMapper::brandToDto)
                .orElseThrow(() -> new ResourceNotFound("Brand with given ID not exist ID: " + id));
    }

    @SneakyThrows
    public BrandDto updateBrand(BrandUpdateDto brandUpdateDto) {
        if (!brandRepository.existsById(brandUpdateDto.id())) {
            throw new ResourceNotFound("Brand with given ID not exist ID: " + brandUpdateDto.id());
        }
        Brand brand = brandMapper.updateDtoToBrand(brandUpdateDto);
        brand = brandRepository.save(brand);
        return brandMapper.brandToDto(brand);
    }

    @SneakyThrows
    public void deleteBrand(Long id) {
        if(!brandRepository.existsById(id)){
            LOGGER.warning("Brand with given ID not exist");
            throw new ResourceNotFound("Brand with given ID not exist");
        } else if (productRepository.findByBrandId(id).isPresent()) {
            LOGGER.info("Brand is in use");
            throw new ResourceInUseException("Brand is in use");
        }
        LOGGER.info("Brand delete success");
        brandRepository.deleteById(id);
    }
}
