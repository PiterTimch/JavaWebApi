package org.example.repository;


import org.example.entities.common.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<ImageEntity, Long> {
}