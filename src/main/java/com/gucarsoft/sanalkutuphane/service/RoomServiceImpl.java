package com.gucarsoft.sanalkutuphane.service;

import com.gucarsoft.sanalkutuphane.model.Room;
import com.gucarsoft.sanalkutuphane.repository.RoomRepository;
import com.gucarsoft.sanalkutuphane.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public ResponseEntity<Room> create(Room room) {
        return new ResponseEntity<Room>(roomRepo.save(room), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Room> getById(Long id) {
        return new ResponseEntity<Room>(roomRepo.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> getUserCount() {
        return new ResponseEntity<Long>((long) userRepo.findAllByOnlineIsTrue().size(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> getUserCountById(Long id) {
        return new ResponseEntity<Long>((long) userRepo.findAllByLastRoom(id).size(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Room>> getAllRooms() {
        return new ResponseEntity<List<Room>>(roomRepo.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Room> deleteById(Long id) {
        Room room= roomRepo.findById(id).orElse(null);
        if(room!=null){
            room.setDeleted(true);
        }
        return new ResponseEntity<Room>(roomRepo.save(room), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Room> makeAvailable(Long id) {
        Room room= roomRepo.findById(id).orElse(null);
        if(room!=null){
            room.setAvailable(true);
        }
        return new ResponseEntity<Room>(roomRepo.save(room), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Room> makeNotAvailable(Long id) {
        Room room= roomRepo.findById(id).orElse(null);
        if(room!=null){
            room.setAvailable(false);
        }
        return new ResponseEntity<Room>(roomRepo.save(room), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Room> makeReadOnly(Long id) {
        Room room= roomRepo.findById(id).orElse(null);
        if(room!=null){
            room.setReadOnly(true);
        }
        return new ResponseEntity<Room>(roomRepo.save(room), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Room> disableReadOnly(Long id) {
        Room room= roomRepo.findById(id).orElse(null);
        if(room!=null){
            room.setReadOnly(false);
        }
        return new ResponseEntity<Room>(roomRepo.save(room), HttpStatus.OK);
    }



}
