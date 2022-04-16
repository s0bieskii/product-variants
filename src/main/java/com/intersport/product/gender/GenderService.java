package com.intersport.product.gender;

import com.intersport.product.gender.dto.GenderAddDto;
import com.intersport.product.gender.dto.GenderUpdateDto;
import com.intersport.product.model.ModelRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GenderService {

    private final GenderRepository genderRepository;
    private final GenderMapper genderMapper;
    private final ModelRepository modelRepository;

    public GenderService(GenderRepository genderRepository, GenderMapper genderMapper,
                         ModelRepository modelRepository) {
        this.genderRepository = genderRepository;
        this.genderMapper = genderMapper;
        this.modelRepository = modelRepository;
    }

    public Gender create(GenderAddDto genderAddDto) {
        Optional<Gender> genderExist = genderRepository.findGenderByNameIgnoreCase(genderAddDto.name());
        if (genderExist.isPresent()) {
            return null;
        }
        return genderRepository.save(genderMapper.addDtoToGender(genderAddDto));
    }

    public List<Gender> getAll() {
        return genderRepository.findAll();
    }

    public Gender getGender(Long id) {
        if (!genderRepository.existsById(id)) {
            return null;
        }
        return genderRepository.findById(id).get();
    }

    public Gender updateGender(GenderUpdateDto genderUpdateDto) {
        if (!genderRepository.existsById(genderUpdateDto.id())) {
            return null;
        }
        Gender gender = genderMapper.updateDtoToGender(genderUpdateDto);
        return genderRepository.save(gender);
    }

    public boolean deleteGender(Long id) {
        if (genderRepository.existsById(id) && modelRepository.findModelByGenderId(id).isPresent()) {
            return false;
        }
        genderRepository.deleteById(id);
        return true;
    }
}
