package com.intersport.product.variant;

import com.intersport.product.model.ModelRepository;
import com.intersport.product.size.SizeRepository;
import com.intersport.product.variant.dto.VariantAddDto;
import com.intersport.product.variant.dto.VariantDto;
import com.intersport.product.variant.dto.VariantMapper;
import com.intersport.product.variant.dto.VariantUpdateDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class VariantService {

    private final VariantRepository variantRepository;
    private final VariantMapper variantMapper;
    private final ModelRepository modelRepository;
    private final SizeRepository sizeRepository;

    public VariantService(VariantRepository variantRepository,
                          VariantMapper variantMapper, ModelRepository modelRepository,
                          SizeRepository sizeRepository) {
        this.variantRepository = variantRepository;
        this.variantMapper = variantMapper;
        this.modelRepository = modelRepository;
        this.sizeRepository = sizeRepository;
    }

    public VariantDto create(VariantAddDto variantAddDto) {
        Variant variant = variantMapper.addDtoToVariant(variantAddDto);
        variant.setModel(modelRepository.getById(variantAddDto.getModelId()));
        variant.setSize(sizeRepository.getById(variantAddDto.getSizeId()));
        variantRepository.save(variant);
        return variantMapper.variantToDto(variant);
    }

    public List<VariantDto> getAll() {
        return variantRepository.findAll().stream().map(variantMapper::variantToDto).collect(Collectors.toList());
    }

    public VariantDto getVariant(Long id) {
        Optional<Variant> variant =  variantRepository.findBySizeId(id);
        if(!variant.isPresent()){
            return null;
        }
        return variantMapper.variantToDto(variant.get());
    }

    public VariantDto update(VariantUpdateDto variantUpdateDto) {
        if(!variantRepository.existsById(variantUpdateDto.getId())) {
            return null;
        }
        Variant variant = variantMapper.updateDtoToVariant(variantUpdateDto);
        variant.setSize(sizeRepository.getById(variantUpdateDto.getSizeId()));
        variant.setModel(modelRepository.getById(variantUpdateDto.getModelId()));
        variant = variantRepository.save(variant);
        return variantMapper.variantToDto(variant);
    }

    public boolean delete(Long id) {
        if(!variantRepository.existsById(id)){
            return false;
        }
        variantRepository.deleteById(id);
        return true;
    }
}
