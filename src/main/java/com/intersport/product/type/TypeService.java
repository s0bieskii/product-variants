package com.intersport.product.type;

import com.intersport.product.model.ModelRepository;
import com.intersport.product.type.Type;
import com.intersport.product.type.dto.TypeAddDto;
import com.intersport.product.type.dto.TypeUpdateDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;
    private final ModelRepository modelRepository;

    public TypeService(TypeRepository typeRepository, TypeMapper typeMapper,
                       ModelRepository modelRepository) {
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
        this.modelRepository = modelRepository;
    }

    //TODO add validation for type with given name exists
    public Type create(TypeAddDto typeAddDto) {
        Type type = typeMapper.addDtoToType(typeAddDto);
        return typeRepository.save(type);
    }

    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    public Type getType(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    public Type updateType(TypeUpdateDto typeUpdateDto) {
        if(!typeRepository.existsById(typeUpdateDto.id())){
            return null;
        }
        Type type = typeMapper.updateDtoToType(typeUpdateDto);
        return typeRepository.save(type);
    }

    public boolean deleteType(Long id) {
        if (typeRepository.existsById(id) && modelRepository.findByTypeId(id).isPresent()) {
            return false;
        }
        typeRepository.deleteById(id);
        return true;
    }
}
