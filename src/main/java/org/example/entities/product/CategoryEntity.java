package org.example.entities.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.entities.common.BaseEntity;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class CategoryEntity extends BaseEntity<Long> {
    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String slug;
}