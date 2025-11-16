package org.example.data;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import com.ibm.icu.text.Transliterator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.data.dto.product.CategoryCreateDTO;
import org.example.services.CategoryService;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class AppDbSeeder {

    private final CategoryService categoryService;

    private final Faker faker = new Faker(new Locale("uk"));
    private final Random random = new Random();

    private final Slugify slugify = Slugify.builder()
            .locale(Locale.forLanguageTag("uk"))
            .transliterator(true)
            .build();

    @PostConstruct
    public void seedData() {
        seedCategories();
    }

    private void seedCategories() {
        int targetCount = 10;

        long existingCount = categoryService.getAll().size();
        if (existingCount >= targetCount) {
            return;
        }

        for (int i = 0; i < targetCount; i++) {
            String name = faker.commerce().department();
            Transliterator transliterator = Transliterator.getInstance("Cyrillic-Latin");
            String latinText = transliterator.transliterate(name);
            String slug = slugify.slugify(latinText);

            try {
                CategoryCreateDTO dto = new CategoryCreateDTO();
                dto.setName(name);
                dto.setSlug(slug);

                categoryService.create(dto);
                System.out.println("Додано категорію: " + name + " (" + slug + ")");
            } catch (IllegalArgumentException e) {
                i--;
            }
        }
    }
}
