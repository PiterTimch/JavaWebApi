package org.example.services;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.data.dto.location.CityCreateDTO;
import org.example.data.dto.location.CityItemDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.example.data.mappers.CityMapper;
import org.example.entities.location.CityEntity;
import org.example.entities.location.CountryEntity;
import org.example.repository.ICityRepository;
import org.example.repository.ICountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final ICityRepository cityRepository;
    private final CityMapper cityMapper;
    private final FileService fileService;
    private final ICountryRepository countryRepository;

    @Transactional
    public CityItemDTO create(CityCreateDTO dto, @Nullable HttpServletRequest request) {
        if (cityRepository.existsBySlug(dto.getSlug())) {
            throw new IllegalArgumentException("Категорія зі slug '" + dto.getSlug() + "' вже існує");
        }

        CountryEntity country = countryRepository.findById(dto.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Країна з ID " + dto.getCountryId() + " не знайдена.")); // Або киньте інший виняток

        CityEntity entity = cityMapper.fromCreateDTO(dto);

        entity.setCountry(country);

        if (dto.getImage() != null) {
            String fileName = fileService.load(dto.getImage());
            entity.setImage(fileName);
        }

        if (dto.getDescription() != null && !dto.getDescription().isBlank() && request != null) {
            String processedDescription = processDescriptionImages(dto.getDescription(), request);
            entity.setDescription(processedDescription);
        }

        CityEntity saved = cityRepository.save(entity);
        return cityMapper.toDto(saved);
    }

    public List<CityItemDTO> getAll() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }

    public String processDescriptionImages(String html, HttpServletRequest request) {
        Document doc = Jsoup.parseBodyFragment(html);
        Elements images = doc.select("img");

        String baseUrl = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            baseUrl += ":" + request.getServerPort();
        }

        for (Element img : images) {
            String src = img.attr("src");
            if (src != null && !src.isBlank() && src.startsWith("http")) {
                String serverFileName = fileService.load(src);
                img.attr("src", baseUrl + "/uploads/large/" + serverFileName);
            }
        }

        return doc.body().html();
    }
}
