package com.gucarsoft.sanalkutuphane.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gucarsoft.sanalkutuphane.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class User extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private String password;

    private boolean online;
    private Long lastRoom;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean canSendMessage;
    private Long reportCount;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }



}
