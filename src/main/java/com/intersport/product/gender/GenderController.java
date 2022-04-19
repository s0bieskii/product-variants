package com.intersport.product.gender;

import com.intersport.product.gender.dto.GenderAddDto;
import com.intersport.product.gender.dto.GenderDto;
import com.intersport.product.gender.dto.GenderUpdateDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
        GenderDto gender = genderService.create(genderAddDto);
        return ResponseEntity.created(new URI("/api/genders/" + gender.id())).body(gender);
    }

    @GetMapping
    public ResponseEntity<List<GenderDto>> getAllGenders() {
        List<GenderDto> genders = genderService.getAll();
        return ResponseEntity.ok(genders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenderDto> getGender(@PathVariable Long id) {
        GenderDto gender = genderService.getGender(id);
        return ResponseEntity.ok(gender);
    }

    @PatchMapping
    public ResponseEntity<GenderDto> updateGender(@RequestBody GenderUpdateDto genderUpdateDto) {
        GenderDto genderAfterUpdate = genderService.updateGender(genderUpdateDto);
        return ResponseEntity.ok(genderAfterUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGender(@PathVariable Long id) {
        genderService.deleteGender(id);
        return ResponseEntity.noContent().build();
    }

}
