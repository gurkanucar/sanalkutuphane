package com.gucarsoft.sanalkutuphane.service;

import com.gucarsoft.sanalkutuphane.model.user.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<User> getByUserName(String username);
    ResponseEntity<User> register(User user);
    ResponseEntity logOut(String username);
    void makeReport(User user);
    ResponseEntity getReportCount(Long id);

}
