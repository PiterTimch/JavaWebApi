package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.location.CityCreateDTO;
import org.example.data.dto.location.CityItemDTO;
import org.example.data.dto.location.CountryCreateDTO;
import org.example.data.dto.location.CountryItemDTO;
import org.example.data.mappers.CityMapper;
import org.example.entities.location.CityEntity;
import org.example.entities.location.CountryEntity;
import org.example.repository.ICityRepository;
import org.example.repository.ICountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final ICityRepository cityRepository;
    private final CityMapper cityMapper;
    private final FileService fileService;
    private final ICountryRepository countryRepository;

    @Transactional
    public CityItemDTO create(CityCreateDTO dto) {
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

        CityEntity saved = cityRepository.save(entity);
        return cityMapper.toDto(saved);
    }

    public List<CityItemDTO> getAll() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }
}
