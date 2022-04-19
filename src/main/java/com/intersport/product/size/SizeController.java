package com.intersport.product.size;

import com.intersport.product.size.dto.SizeAddDto;
import com.intersport.product.size.dto.SizeDto;
import com.intersport.product.size.dto.SizeUpdateDto;
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
@RequestMapping("/api/sizes")
public class SizeController {

    private final SizeService sizeService;

    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @PostMapping
    public ResponseEntity<SizeDto> createSize(@RequestBody SizeAddDto sizeAddDto) throws URISyntaxException {
        SizeDto size = sizeService.create(sizeAddDto);
        return ResponseEntity.created(new URI("/api/sizes/" + size.id())).body(size);
    }

    @GetMapping
    public ResponseEntity<List<SizeDto>> getAllSizes() {
        List<SizeDto> sizes = sizeService.getAll();
        return ResponseEntity.ok(sizes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeDto> getSize(@PathVariable Long id) {
        SizeDto size = sizeService.getSize(id);
        return ResponseEntity.ok(size);
    }

    @PatchMapping
    public ResponseEntity<SizeDto> updateGender(@RequestBody SizeUpdateDto sizeUpdateDto) {
        SizeDto size = sizeService.updateSize(sizeUpdateDto);
        return ResponseEntity.ok(size);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSize(@PathVariable Long id) {
        sizeService.deleteSize(id);
        return ResponseEntity.noContent().build();
    }
}
