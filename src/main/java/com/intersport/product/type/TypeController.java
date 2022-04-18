package com.intersport.product.type;

import com.intersport.product.type.dto.TypeAddDto;
import com.intersport.product.type.dto.TypeDto;
import com.intersport.product.type.dto.TypeUpdateDto;
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
@RequestMapping("/api/type")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping
    public ResponseEntity createType(@RequestBody TypeAddDto typeAddDto) throws URISyntaxException {
        TypeDto type = typeService.create(typeAddDto);
        return ResponseEntity.created(new URI("/api/sizes/" + type.id())).body(type);
    }

    @GetMapping
    public ResponseEntity<List<TypeDto>> getAllTypes() {
        List<TypeDto> sizes = typeService.getAll();
        return ResponseEntity.ok(sizes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDto> getType(@PathVariable Long id) {
        TypeDto size = typeService.getType(id);
        return ResponseEntity.ok(size);
    }

    @PatchMapping
    public ResponseEntity<TypeDto> updateType(@RequestBody TypeUpdateDto sizeUpdateDto) {
        return ResponseEntity.ok(typeService.updateType(sizeUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteType(@PathVariable Long id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }
}
