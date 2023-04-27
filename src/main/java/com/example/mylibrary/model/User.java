package com.example.mylibrary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
    public class User {
        private int id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private UserType userType;
    }

