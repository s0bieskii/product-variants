package com.intersport.product.gender;

import com.intersport.product.gender.dto.GenderAddDto;
import com.intersport.product.gender.dto.GenderUpdateDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genders")
public class GenderController {

    private final GenderService genderService;

    public GenderController(GenderService genderService) {
        this.genderService = genderService;
    }

    @PostMapping
    public ResponseEntity createGender(@RequestBody GenderAddDto genderAddDto) throws URISyntaxException {
        Gender gender = genderService.create(genderAddDto);
        if (gender == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Gender already exists");
        }
        return ResponseEntity.created(new URI("/api/genders/" + gender.getId())).build();
    }

    @GetMapping
    public ResponseEntity getAllGenders() {
        List<Gender> genders = genderService.getAll();
        return ResponseEntity.ok(genders);
    }

    @GetMapping("/{id}")
    public ResponseEntity getGender(@PathVariable Long id) {
        Gender gender = genderService.getGender(id);
        if (gender == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(gender);
    }

    @PatchMapping
    public ResponseEntity updateGender(@RequestBody GenderUpdateDto genderUpdateDto) {
        Gender genderAfterUpdate = genderService.updateGender(genderUpdateDto);
        if (genderAfterUpdate == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(genderAfterUpdate);
    }

    @DeleteMapping
    public ResponseEntity deleteGender(@PathVariable Long id) {
        boolean deleteSuccess = genderService.deleteGender(id);
        if(!deleteSuccess){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource is in use");
        }
        return ResponseEntity.noContent().build();
    }

}
