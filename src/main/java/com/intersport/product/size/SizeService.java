package com.intersport.product.size;

import com.intersport.product.category.CategoryRepository;
import com.intersport.product.size.dto.SizeAddDto;
import com.intersport.product.size.dto.SizeUpdateDto;
import com.intersport.product.variant.VariantRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SizeService {

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


    //TODO add validation for size exists wit hgiven name
    public Size create(SizeAddDto sizeAddDto) {
        if (!categoryRepository.findById(sizeAddDto.categoryId()).isPresent()) {
            return null;
        }
        Size size = sizeMapper.addDtoToSize(sizeAddDto);
        size.setCategory(categoryRepository.getById(sizeAddDto.categoryId()));
        return sizeRepository.save(size);
    }

    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    public Size getSize(Long id) {
        return sizeRepository.findById(id).orElse(null);
    }

    public Size updateSize(SizeUpdateDto sizeUpdateDto) {
        Size size = sizeMapper.updateDtoToSize(sizeUpdateDto);
        size.setCategory(categoryRepository.getById(sizeUpdateDto.categoryId()));
        return sizeRepository.save(size);
    }

    public boolean deleteSize(Long id) {
        if (variantRepository.findBySizeId(id).isPresent()) {
            return false;
        }
        sizeRepository.deleteById(id);
        return true;
    }
}
