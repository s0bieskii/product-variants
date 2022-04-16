package com.intersport.product.brand;

import com.intersport.product.brand.dto.BrandAddDto;
import com.intersport.product.brand.dto.BrandDto;
import com.intersport.product.brand.dto.BrandUpdateDto;
import com.intersport.product.product.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final ProductRepository productRepository;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper, ProductRepository productRepository) {
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
        this.productRepository = productRepository;
    }

    public BrandDto create(BrandAddDto brandAddDto) {
        if (brandRepository.findBrandByName(brandAddDto.name()).isPresent()) {
            return null;
        }
        Brand brand = brandMapper.addDtoToBrand(brandAddDto);
        Brand savedBrand = brandRepository.save(brand);
        return brandMapper.brandToDto(savedBrand);
    }


    public List<BrandDto> getAll() {
        return brandRepository.findAll().stream().map(brandMapper::brandToDto).collect(Collectors.toList());
    }

    public BrandDto getBrand(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if (!brand.isPresent()) {
            return null;
        }
        return brandMapper.brandToDto(brand.get());
    }

    public BrandDto updateBrand(BrandUpdateDto brandUpdateDto) {
        if (brandRepository.existsById(brandUpdateDto.id())) {
            return null;
        }
        Brand brand = brandMapper.updateDtoToBrand(brandUpdateDto);
        return brandMapper.brandToDto(brandRepository.save(brand));
    }


    public boolean deleteBrand(Long id) {
        if (brandRepository.existsById(id) && productRepository.findByBrandId(id).isPresent()) {
            return false;
        }
        brandRepository.deleteById(id);
        return true;
    }
}
