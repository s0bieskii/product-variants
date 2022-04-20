package com.intersport.product.variant;

import com.intersport.product.model.ModelRepository;
import com.intersport.product.size.SizeRepository;
import com.intersport.product.utils.exceptions.ResourceNotFound;
import com.intersport.product.variant.dto.VariantAddDto;
import com.intersport.product.variant.dto.VariantDto;
import com.intersport.product.variant.dto.VariantMapper;
import com.intersport.product.variant.dto.VariantUpdateDto;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class VariantService {

    public static final Logger LOGGER = Logger.getLogger(VariantService.class.getName());
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

    @SneakyThrows
    public VariantDto create(VariantAddDto variantAddDto) {
        if (!modelRepository.existsById(variantAddDto.getModelId()) ||
                !sizeRepository.existsById(variantAddDto.getSizeId())) {
            LOGGER.info("Nested resource not found");
            throw new ResourceNotFound("Nested resource not found");
        }
        Variant variant = variantMapper.addDtoToVariant(variantAddDto);
        variant.setModel(modelRepository.getById(variantAddDto.getModelId()));
        variant.setSize(sizeRepository.getById(variantAddDto.getSizeId()));
        variantRepository.save(variant);
        LOGGER.info("Variant create success " + variant);
        return variantMapper.variantToDto(variant);
    }

    public List<VariantDto> getAll() {
        List<Variant> variants = variantRepository.findAll();
        LOGGER.info("getAll FOUND: " + variants.size() + " variants");
        return variants.stream().map(variantMapper::variantToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public VariantDto getVariant(Long id) {
        LOGGER.info("getModel ID: " + id);
        return variantRepository.findById(id).map(variantMapper::variantToDto)
                .orElseThrow(() -> new ResourceNotFound("Variant with given ID not exist ID: " + id));
    }

    @SneakyThrows
    public VariantDto update(VariantUpdateDto variantUpdateDto) {
        if (!variantRepository.findById(variantUpdateDto.getId()).isPresent()) {
            throw new ResourceNotFound("Model with given ID not exist ID: " + variantUpdateDto.getId());
        } else if (!modelRepository.existsById(variantUpdateDto.getModelId()) ||
                !sizeRepository.existsById(variantUpdateDto.getSizeId())) {
            LOGGER.info("Nested resource not found");
            throw new ResourceNotFound("Nested resource not found");
        }
        Variant variant = variantMapper.updateDtoToVariant(variantUpdateDto);
        variant.setSize(sizeRepository.getById(variantUpdateDto.getSizeId()));
        variant.setModel(modelRepository.getById(variantUpdateDto.getModelId()));
        variant = variantRepository.save(variant);
        LOGGER.info("Variant update success :" + variant);
        return variantMapper.variantToDto(variant);
    }

    @SneakyThrows
    public void delete(Long id) {
        if (!variantRepository.existsById(id)) {
            LOGGER.info("Variant with given ID not exist ID: " + id);
            throw new ResourceNotFound("Variant with given ID not exist ID: " + id);
        }
        LOGGER.info("Delete success");
        variantRepository.deleteById(id);
    }
}
