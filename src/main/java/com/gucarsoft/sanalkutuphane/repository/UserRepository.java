package com.gucarsoft.sanalkutuphane.repository;

import com.gucarsoft.sanalkutuphane.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAllByLastRoom(Long id);

    List<User> findAllByOnlineIsTrue();
}
