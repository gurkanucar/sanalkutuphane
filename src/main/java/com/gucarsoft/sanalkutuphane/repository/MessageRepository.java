package com.gucarsoft.sanalkutuphane.repository;

import com.gucarsoft.sanalkutuphane.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findAllByRoom_IdAndDeletedFalse(Long id);

}
