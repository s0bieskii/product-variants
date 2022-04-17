package com.intersport.product.type;

import com.intersport.product.type.dto.TypeAddDto;
import com.intersport.product.type.dto.TypeUpdateDto;
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
@RequestMapping("/api/type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping
    public ResponseEntity createType(@RequestBody TypeAddDto typeAddDto) throws URISyntaxException {
        Type type = typeService.create(typeAddDto);
        if (type == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Type already exists");
        }
        return ResponseEntity.created(new URI("/api/sizes/" + type.getId())).body(type);
    }

    @GetMapping
    public ResponseEntity getAllTypes() {
        List<Type> sizes = typeService.getAll();
        return ResponseEntity.ok(sizes);
    }

    @GetMapping("/{id}")
    public ResponseEntity getType(@PathVariable Long id) {
        Type size = typeService.getType(id);
        if (size == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(size);
    }

    @PatchMapping
    public ResponseEntity updateType(@RequestBody TypeUpdateDto sizeUpdateDto) {
        Type size = typeService.updateType(sizeUpdateDto);
        if (size == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(size);
    }

    @DeleteMapping
    public ResponseEntity deleteType(@PathVariable Long id) {
        boolean deleteSuccess = typeService.deleteType(id);
        if (!deleteSuccess) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource is in use");
        }
        return ResponseEntity.noContent().build();
    }
}
