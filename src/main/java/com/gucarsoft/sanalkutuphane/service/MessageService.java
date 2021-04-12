package com.gucarsoft.sanalkutuphane.service;

import com.gucarsoft.sanalkutuphane.model.message.Message;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {

    ResponseEntity<Message> create(Message message);
    ResponseEntity<Message> getById(Long id);
    ResponseEntity<List<Message>> getAll(Long id);
    ResponseEntity<Message> deleteById(Long id);
    void makeReport(Long id);
    ResponseEntity<Integer> getReportCount(Long id);


}
