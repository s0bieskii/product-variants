package com.intersport.product.model;

import com.intersport.product.gender.GenderRepository;
import com.intersport.product.model.dto.ModelAddDto;
import com.intersport.product.model.dto.ModelDto;
import com.intersport.product.model.dto.ModelMapper;
import com.intersport.product.model.dto.ModelUpdateDto;
import com.intersport.product.type.TypeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final TypeRepository typeRepository;
    private final GenderRepository genderRepository;

    public ModelService(ModelRepository modelRepository, ModelMapper modelMapper,
                        TypeRepository typeRepository, GenderRepository genderRepository) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.typeRepository = typeRepository;
        this.genderRepository = genderRepository;
    }

    public ModelDto create(ModelAddDto modelAddDto) {
        Model model = modelMapper.addDtoToModel(modelAddDto);
        model.setType(typeRepository.getById(modelAddDto.getTypeId()));
        model.setGender(genderRepository.getById(modelAddDto.getGenderId()));
        return modelMapper.modelToDto(model);
    }

    public List<ModelDto> getAll() {
        return modelRepository.findAll().stream().map(modelMapper::modelToDto).collect(Collectors.toList());
    }

    public ModelDto getModel(Long id) {
        return modelRepository.findById(id).map(modelMapper::modelToDto).orElse(null);
    }

    public ModelDto updateModel(ModelUpdateDto modelUpdateDto) {
        if (!modelRepository.existsById(modelUpdateDto.getId())) {
            return null;
        }
        Model model = modelMapper.updateDtoToModel(modelUpdateDto);
        model.setGender(genderRepository.getById(modelUpdateDto.getGenderId()));
        model.setType(typeRepository.getById(modelUpdateDto.getTypeId()));
        model = modelRepository.save(model);
        return modelMapper.modelToDto(model);
    }

    public boolean deleteModel(Long id) {
        if (!modelRepository.existsById(id)) {
            return false;
        }
        modelRepository.deleteById(id);
        return true;

    }
}
