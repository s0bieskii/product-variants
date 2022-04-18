package com.intersport.product.size;

import com.intersport.product.size.dto.SizeAddDto;
import com.intersport.product.size.dto.SizeUpdateDto;
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
@RequestMapping("/api/sizes")
public class SizeController {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @PostMapping
    public ResponseEntity createSize(@RequestBody SizeAddDto sizeAddDto) throws URISyntaxException {
        Size size = sizeService.create(sizeAddDto);
        if (size == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Size already exists");
        }
        return ResponseEntity.created(new URI("/api/sizes/" + size.getId())).body(size);
    }

    @GetMapping
    public ResponseEntity getAllSizes() {
        List<Size> sizes = sizeService.getAll();
        return ResponseEntity.ok(sizes);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSize(@PathVariable Long id) {
        Size size = sizeService.getSize(id);
        if (size == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(size);
    }

    @PatchMapping
    public ResponseEntity updateGender(@RequestBody SizeUpdateDto sizeUpdateDto) {
        Size size = sizeService.updateSize(sizeUpdateDto);
        if (size == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSize(@PathVariable Long id) {
        boolean deleteSuccess = sizeService.deleteSize(id);
        if (!deleteSuccess) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource is in use");
        }
        return ResponseEntity.noContent().build();
    }
}
