package org.example.data.dto.location;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CountryCreateDTO {
    @NotBlank(message = "Ім'я обов'язкове")
    private String name;

    @NotBlank(message = "Slug обов'язковий")
    private String slug;

    @NotBlank(message = "Код обов'язковий")
    private String code;

    private MultipartFile image;
}
