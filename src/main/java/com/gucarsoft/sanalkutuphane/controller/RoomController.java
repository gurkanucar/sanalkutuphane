package com.gucarsoft.sanalkutuphane.controller;

import com.gucarsoft.sanalkutuphane.model.Room;
import com.gucarsoft.sanalkutuphane.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    RoomService service;


    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return service.getAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room) {
        return service.create(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteUserById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/userCount/{id}")
    public ResponseEntity<Long> getUserCount(@PathVariable Long id) {
        return service.getUserCountById(id);
    }

    @GetMapping("/userCount")
    public ResponseEntity<Long> getUserCount() {
        return service.getUserCount();
    }


    @GetMapping("/available/true/{id}")
    public ResponseEntity<Room> makeAvailableTrue(@PathVariable Long id) {
        return service.makeAvailable(id);
    }


    @GetMapping("/available/false/{id}")
    public ResponseEntity<Room> makeAvailableFalse(@PathVariable Long id) {
        return service.makeNotAvailable(id);
    }


    @GetMapping("/readOnly/true/{id}")
    public ResponseEntity<Room> makeReadOnlyTrue(@PathVariable Long id) {
        return service.makeReadOnly(id);
    }

    @GetMapping("/readOnly/false/{id}")
    public ResponseEntity<Room> makeReadOnlyFalse(@PathVariable Long id) {
        return service.disableReadOnly(id);
    }


}



