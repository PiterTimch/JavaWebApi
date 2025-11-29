package org.example.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import com.ibm.icu.text.Transliterator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.data.dto.location.CityCreateDTO;
import org.example.data.dto.product.CategoryCreateDTO;
import org.example.data.seed.CitySeedModel;
import org.example.services.CategoryService;
import org.example.services.CityService;
import org.example.services.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class AppDbSeeder {

    private final CategoryService categoryService;
    private final CityService cityService;
    private final FileService fileService;

    private final Faker faker = new Faker(new Locale("uk"));
    private final Random random = new Random();

    private final Slugify slugify = Slugify.builder()
            .locale(Locale.forLanguageTag("uk"))
            .transliterator(true)
            .build();

    @PostConstruct
    public void seedData() {
        seedCategories();
        seedCitiesFromJson();
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

    private void seedCitiesFromJson() {
        try {
            if (!cityService.getAll().isEmpty()) {
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource("data/cities.json").getInputStream();

            List<CitySeedModel> cities = mapper.readValue(
                    inputStream,
                    new TypeReference<>() {}
            );

            Transliterator transliterator = Transliterator.getInstance("Cyrillic-Latin");

            for (CitySeedModel c : cities) {

                String latin = transliterator.transliterate(c.getName());
                String slug = slugify.slugify(latin);

                CityCreateDTO dto = new CityCreateDTO();
                dto.setName(c.getName());
                dto.setSlug(slug);
                dto.setCountryId(c.getCountryId());
                dto.setPopulation(c.getPopulation());
                dto.setTimezone(c.getTimezone());
                dto.setMainAirportCode(c.getMainAirportCode());
                dto.setAvgMealPrice(c.getAvgMealPrice());
                dto.setAvgHotelPrice(c.getAvgHotelPrice());
                dto.setHasRecreationalWater(c.getHasRecreationalWater());

                if (c.getImageUrl() != null && !c.getImageUrl().isEmpty()) {
                    String fileName = fileService.load(c.getImageUrl());
                    dto.setImage(null);
                }

                var saved = cityService.create(dto, null);

                if (c.getImageUrl() != null && !c.getImageUrl().isEmpty()) {
                    String fileName = fileService.load(c.getImageUrl());
                    saved.setImage(fileName);
                }

                System.out.println("Додано місто: " + c.getName() + " (" + slug + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Помилка при сідингу міст");
        }
    }
}
