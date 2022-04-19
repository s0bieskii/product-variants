package com.intersport.product.gender;

import com.intersport.product.gender.dto.GenderAddDto;
import com.intersport.product.gender.dto.GenderDto;
import com.intersport.product.gender.dto.GenderMapper;
import com.intersport.product.gender.dto.GenderUpdateDto;
import com.intersport.product.model.ModelRepository;
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
public class GenderService {

    public static final Logger LOGGER = Logger.getLogger(GenderService.class.getName());
    private final GenderRepository genderRepository;
    private final GenderMapper genderMapper;
    private final ModelRepository modelRepository;

    public GenderService(GenderRepository genderRepository, GenderMapper genderMapper,
                         ModelRepository modelRepository) {
        this.genderRepository = genderRepository;
        this.genderMapper = genderMapper;
        this.modelRepository = modelRepository;
    }

    @SneakyThrows
    public GenderDto create(GenderAddDto genderAddDto) {
        LOGGER.info("create " + genderAddDto);
        Optional<Gender> genderExist = genderRepository.findByNameIgnoreCase(genderAddDto.name());
        if (genderExist.isPresent()) {
            LOGGER.info("Gender with given name already exist NAME" + genderAddDto.name());
            throw new ResourceExistException("Gender with given name already exist");
        }
        Gender gender = genderMapper.addDtoToGender(genderAddDto);
        gender = genderRepository.save(gender);
        LOGGER.info("Gender create success " + gender);
        return genderMapper.genderToDto(gender);
    }

    public List<GenderDto> getAll() {
        List<Gender> genders = genderRepository.findAll();
        LOGGER.info("getAll FOUND: " + genders.size() + "genders");
        return genders.stream().map(genderMapper::genderToDto).collect(Collectors.toList());
    }

    @SneakyThrows
    public GenderDto getGender(Long id) {
        LOGGER.info("getGender with ID: " + id);
        return genderRepository.findById(id).map(genderMapper::genderToDto)
                .orElseThrow(() -> new ResourceNotFound("Gender with given ID not exist ID: " + id));
    }

    @SneakyThrows
    public GenderDto updateGender(GenderUpdateDto genderUpdateDto) {
        if (!genderRepository.existsById(genderUpdateDto.id())) {
            throw new ResourceNotFound("Gender with given ID not exist ID: " + genderUpdateDto.id());
        }
        Gender gender = genderMapper.updateDtoToGender(genderUpdateDto);
        gender = genderRepository.save(gender);
        LOGGER.info("Gender after update: " + gender);
        return genderMapper.genderToDto(gender);
    }

    @SneakyThrows
    public void deleteGender(Long id) {
        LOGGER.info("Delete gender with ID: " + id);
        if (!genderRepository.existsById(id)) {
            LOGGER.warning("Gender with given ID not exist");
            throw new ResourceNotFound("Gender with given ID not exist");
        } else if (!modelRepository.findByGenderId(id).isEmpty()) {
            LOGGER.info("Gender is in use");
            throw new ResourceInUseException("Gender is in use");
        }
        LOGGER.info("Gender delete success");
        genderRepository.deleteById(id);
    }
}
