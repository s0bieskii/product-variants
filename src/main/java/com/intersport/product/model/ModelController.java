package com.intersport.product.model;

import com.intersport.product.model.dto.ModelAddDto;
import com.intersport.product.model.dto.ModelDto;
import com.intersport.product.model.dto.ModelUpdateDto;
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
@RequestMapping("/api/models")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity createModel(@RequestBody ModelAddDto modelAddDto) throws URISyntaxException {
        ModelDto model = modelService.create(modelAddDto);
        return ResponseEntity.created(new URI("/api/genders/" + model.getId())).body(model);
    }

    @GetMapping
    public ResponseEntity getAllModels() {
        List<ModelDto> models = modelService.getAll();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/{id}")
    public ResponseEntity getModel(@PathVariable Long id) {
        ModelDto model = modelService.getModel(id);
        if (model == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(model);
    }

    @PatchMapping
    public ResponseEntity updateModel(@RequestBody ModelUpdateDto modelUpdateDto) {
        ModelDto genderAfterUpdate = modelService.updateModel(modelUpdateDto);
        if (genderAfterUpdate == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(genderAfterUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity.noContent().build();
    }
}
