package com.gucarsoft.sanalkutuphane.model.user;

import lombok.Data;

@Data
public class JwtRequestModel {
    private String username;
    private String password;
}
