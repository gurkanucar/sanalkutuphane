package com.gucarsoft.sanalkutuphane.controller;

import com.gucarsoft.sanalkutuphane.model.Room;
import com.gucarsoft.sanalkutuphane.model.message.Message;
import com.gucarsoft.sanalkutuphane.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageService service;


    @GetMapping("/room/{id}")
    public ResponseEntity<List<Message>> getAll(@PathVariable Long id) {
        return service.getAll(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Message> create(@RequestBody Message message) {
        return service.create(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteUserById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/report/{id}")
    public void makeReport(@PathVariable Long id) {
        service.makeReport(id);
    }

    @GetMapping("/reportCount/{id}")
    public ResponseEntity<Integer> getUserCount(@PathVariable Long id) {
        return service.getReportCount(id);
    }

}
