package com.gucarsoft.sanalkutuphane.service;

import com.gucarsoft.sanalkutuphane.helper.JwtUtil;
import com.gucarsoft.sanalkutuphane.model.user.Role;
import com.gucarsoft.sanalkutuphane.model.user.User;
import com.gucarsoft.sanalkutuphane.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public ResponseEntity<User> getByUserName(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<User> register(User user) {
        User existingUsername = userRepo.findByUsername(user.getUsername());
        if (existingUsername != null) {
            return new ResponseEntity("User already exist!", HttpStatus.CONFLICT);
        }

        User existingEmail = userRepo.findByEmail(user.getEmail());
        if (existingEmail != null) {
            return new ResponseEntity("User already exist!", HttpStatus.CONFLICT);
        }

        user.setRole(Role.USER);
        user.setOnline(true);
        user.setLastRoom(0L);
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity logOut(String username) {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            user.setLastRoom(0L);
            user.setOnline(false);
            userRepo.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public void makeReport(User user) {
        user.setReportCount(user.getReportCount()+1L);
        userRepo.save(user);
    }

    @Override
    public ResponseEntity getReportCount(Long id) {
        Long reportCount=userRepo.findById(id).get().getReportCount();
        return new ResponseEntity(reportCount,HttpStatus.OK);
    }


}
