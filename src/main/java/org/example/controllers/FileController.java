package org.example.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.data.dto.account.LoginDTO;
import org.example.data.dto.account.RegisterUserDTO;
import org.example.data.dto.account.UserItemDTO;
import org.example.data.dto.file.SaveImageFileDTO;
import org.example.services.AccountService;
import org.example.services.FileService;
import org.example.validators.helpers.ValidatedDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
@Tag(name = "File")
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/saveImage", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveImage(@ModelAttribute SaveImageFileDTO dto) {
        return ResponseEntity.ok(fileService.load(dto.getImageFile()));
    }
}