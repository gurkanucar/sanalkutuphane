package com.gucarsoft.sanalkutuphane.service;

import com.gucarsoft.sanalkutuphane.model.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {

    ResponseEntity<Room> create(Room room);
    ResponseEntity<Room> getById(Long id);
    ResponseEntity<Long> getUserCountById(Long id);
    ResponseEntity<Long> getUserCount();
    ResponseEntity<List<Room>> getAllRooms();
    ResponseEntity<Room> deleteById(Long id);
    ResponseEntity<Room> makeReadOnly(Long id);
    ResponseEntity<Room> disableReadOnly(Long id);
    ResponseEntity<Room> makeAvailable(Long id);
    ResponseEntity<Room> makeNotAvailable(Long id);


}
