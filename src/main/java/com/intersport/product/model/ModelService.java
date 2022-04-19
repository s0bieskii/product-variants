package com.intersport.product.model;

import com.intersport.product.gender.GenderRepository;
import com.intersport.product.model.dto.ModelAddDto;
import com.intersport.product.model.dto.ModelDto;
import com.intersport.product.model.dto.ModelMapper;
import com.intersport.product.model.dto.ModelUpdateDto;
import com.intersport.product.type.TypeRepository;
import com.intersport.product.utils.exceptions.ResourceExistException;
import com.intersport.product.utils.exceptions.ResourceNotFound;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    public static final Logger LOGGER = Logger.getLogger(ModelService.class.getName());
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

    @SneakyThrows
    public ModelDto create(ModelAddDto modelAddDto) {
        if (!genderRepository.existsById(modelAddDto.getGenderId()) ||
                !typeRepository.existsById(modelAddDto.getTypeId())) {
            LOGGER.info("Nested resource not found");
            throw new ResourceNotFound("Nested resource not found");
        }
        Model model = modelMapper.addDtoToModel(modelAddDto);
        model.setType(typeRepository.getById(modelAddDto.getTypeId()));
        model.setGender(genderRepository.getById(modelAddDto.getGenderId()));
        model = modelRepository.save(model);
        LOGGER.info("Model create success " + model);
        return modelMapper.modelToDto(model);
    }

    public List<ModelDto> getAll() {
        List<Model> models = modelRepository.findAll();
        LOGGER.info("getAll FOUND: " + models.size() + " models");
        return models.stream().map(modelMapper::modelToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public ModelDto getModel(Long id) {
        LOGGER.info("getModel ID: " + id);
        return modelRepository.findById(id).map(modelMapper::modelToDto)
                .orElseThrow(() -> new ResourceNotFound("Model with given ID not exist ID: " + id));
    }

    @SneakyThrows
    public ModelDto updateModel(ModelUpdateDto modelUpdateDto) {
        if (!modelRepository.existsById(modelUpdateDto.getId())) {
            throw new ResourceNotFound("Model with given ID not exist ID: " + modelUpdateDto.getId());
        } else if (!typeRepository.existsById(modelUpdateDto.getTypeId()) ||
                !genderRepository.existsById(modelUpdateDto.getGenderId())) {
            throw new ResourceExistException("Nested resource not found");
        }
        Model model = modelMapper.updateDtoToModel(modelUpdateDto);
        model.setGender(genderRepository.getById(modelUpdateDto.getGenderId()));
        model.setType(typeRepository.getById(modelUpdateDto.getTypeId()));
        model = modelRepository.save(model);
        LOGGER.info("Model update success :" + model);
        return modelMapper.modelToDto(model);
    }

    @SneakyThrows
    public void deleteModel(Long id) {
        if (!modelRepository.existsById(id)) {
            LOGGER.info("Model with given ID not exist ID: " + id);
            throw new ResourceNotFound("Model with given ID not exist ID: " + id);
        }
        LOGGER.info("Delete success");
        modelRepository.deleteById(id);
    }
}
