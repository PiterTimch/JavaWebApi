package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.data.dto.location.CountryCreateDTO;
import org.example.data.dto.location.CountryItemDTO;
import org.example.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
@Tag(name = "Countries", description = "Країни")
public class CountryController {
    private final CountryService countryService;

    @Operation(summary = "Створити нову країну")
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CountryItemDTO> create(@RequestBody CountryCreateDTO dto) {
        return ResponseEntity.ok(countryService.create(dto));
    }

    @Operation(summary = "Отримати список всіх країн")
    @GetMapping
    public ResponseEntity<List<CountryItemDTO>> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @Operation(summary = "Отримати країну за slug")
    @GetMapping("/{slug}")
    public ResponseEntity<CountryItemDTO> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(countryService.getBySlug(slug));
    }
}
