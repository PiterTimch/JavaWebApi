package org.example.data.dto.account;

import lombok.Data;

@Data
public class UserItemDTO {
    private Long id;
    private String lastName;
    private String name;
    private String email;
    private String image;
    private String phone;
}