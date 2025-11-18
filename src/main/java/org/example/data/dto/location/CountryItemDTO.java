package org.example.data.dto.location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryItemDTO {
    private Long id;

    private String name;

    private String slug;

    private String code;

    private String image;

    private String dateCreated;
}
