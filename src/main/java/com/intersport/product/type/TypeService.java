package com.intersport.product.type;

import com.intersport.product.model.ModelRepository;
import com.intersport.product.type.dto.TypeAddDto;
import com.intersport.product.type.dto.TypeDto;
import com.intersport.product.type.dto.TypeMapper;
import com.intersport.product.type.dto.TypeUpdateDto;
import com.intersport.product.utils.exceptions.ResourceExistException;
import com.intersport.product.utils.exceptions.ResourceInUseException;
import com.intersport.product.utils.exceptions.ResourceNotFound;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    public static final Logger LOGGER = Logger.getLogger(TypeService.class.getName());
    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;
    private final ModelRepository modelRepository;

    public TypeService(TypeRepository typeRepository, TypeMapper typeMapper,
                       ModelRepository modelRepository) {
        LOGGER.info("Create TypeService");
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
        this.modelRepository = modelRepository;
    }

    @SneakyThrows
    public TypeDto create(TypeAddDto typeAddDto) {
        LOGGER.info("Type create: " + typeAddDto);
        if (typeRepository.findByNameIgnoreCase(typeAddDto.name()).isPresent()) {
            LOGGER.warning("Type with given name exist NAME: " + typeAddDto.name());
            throw new ResourceExistException("Type with given name already exist");
        }
        Type type = typeMapper.addDtoToType(typeAddDto);
        type = typeRepository.save(type);
        return typeMapper.typeToDto(type);
    }

    public List<TypeDto> getAll() {
        List<Type> types = typeRepository.findAll();
        LOGGER.info("getAll FOUND: " + types.size() + "types");
        return types.stream().map(typeMapper::typeToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public TypeDto getType(Long id) {
        LOGGER.info("getType with ID: " + id);
        return typeRepository.findById(id).map(typeMapper::typeToDto)
                .orElseThrow(() -> new ResourceNotFound("Type with given ID not exist"));
    }

    @SneakyThrows
    public TypeDto updateType(TypeUpdateDto typeUpdateDto) {
        LOGGER.info("updateType :" + typeUpdateDto);
        if (!typeRepository.existsById(typeUpdateDto.id())) {
            LOGGER.warning("Type with given ID not exist");
            throw new ResourceNotFound("Type with given ID not exist");
        }
        Type type = typeMapper.updateDtoToType(typeUpdateDto);
        type = typeRepository.save(type);
        LOGGER.info("Type after update: " + type);
        return typeMapper.typeToDto(type);
    }

    @SneakyThrows
    public void deleteType(Long id) {
        LOGGER.info("Delete type with ID: " + id);
        if (!typeRepository.existsById(id)) {
            LOGGER.warning("Type with given ID not exist");
            throw new ResourceNotFound("Type with given ID not exist");
        } else if (modelRepository.findByTypeId(id).isPresent()) {
            LOGGER.info("Type is in use");
            throw new ResourceInUseException("Type is in use");
        }
        LOGGER.info("Type delete success");
        typeRepository.deleteById(id);
    }
}
