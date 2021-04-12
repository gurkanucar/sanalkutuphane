package com.gucarsoft.sanalkutuphane.model.user;

import com.gucarsoft.sanalkutuphane.model.BaseEntity;
import lombok.Data;
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
    private String password;
    private boolean isOnline;
    private Long lastRoom;

    @Enumerated(EnumType.STRING)
    private Role role;


    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

}
