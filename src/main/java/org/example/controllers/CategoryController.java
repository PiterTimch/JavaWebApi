package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.data.dto.product.CategoryCreateDTO;
import org.example.data.dto.product.CategoryItemDTO;
import org.example.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Категорії товарів")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Створити нову категорію")
    @PostMapping
    public ResponseEntity<CategoryItemDTO> create(@RequestBody CategoryCreateDTO dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @Operation(summary = "Отримати список всіх категорій")
    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @Operation(summary = "Отримати категорію за slug")
    @GetMapping("/{slug}")
    public ResponseEntity<CategoryItemDTO> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(categoryService.getBySlug(slug));
    }
}
