package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.data.dto.file.SavedImageDTO;
import org.example.data.mappers.ImageMapper;
import org.example.entities.common.ImageEntity;
import org.example.repository.IImageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageDbService {
    private final IImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public SavedImageDTO save(String fileName) {
        ImageEntity img = new ImageEntity();
        img.setImageName(fileName);

        img = imageRepository.save(img);

        return imageMapper.toDto(img);
    }
}
