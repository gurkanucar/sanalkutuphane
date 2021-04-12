package com.gucarsoft.sanalkutuphane.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class CreateUserModel {
    private String name;
    private String username;
    private String email;

    @Setter(AccessLevel.NONE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
