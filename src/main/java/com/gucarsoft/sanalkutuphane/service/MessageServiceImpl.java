package com.gucarsoft.sanalkutuphane.service;

import com.gucarsoft.sanalkutuphane.model.Room;
import com.gucarsoft.sanalkutuphane.model.message.Message;
import com.gucarsoft.sanalkutuphane.model.user.User;
import com.gucarsoft.sanalkutuphane.repository.MessageRepository;
import com.gucarsoft.sanalkutuphane.repository.RoomRepository;
import com.gucarsoft.sanalkutuphane.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    UserRepository userRepository;

    public String getAuthUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @Override
    public ResponseEntity<Message> create(Message message) {
        Room room=roomRepo.findById(message.getRoom().getId()).orElse(null);
        User user = userRepository.findByUsername(getAuthUserName());
        message.setUser(user);

        if(room.isAvailable() && !room.isReadOnly()){
            user.setOnline(true);
            user.setLastRoom(room.getId());
            return new ResponseEntity<Message>(messageRepo.save(message), HttpStatus.OK);
        }
        return new ResponseEntity<Message>(HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    public ResponseEntity<Message> getById(Long id) {
        return new ResponseEntity<Message>(messageRepo.findById(id).orElse(null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Message>> getAll(Long id) {
        return new ResponseEntity<List<Message>>(messageRepo.findAllByRoom_IdAndDeletedFalse(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Message> deleteById(Long id) {
        Message message= messageRepo.findById(id).orElse(null);
        if(message!=null){
            message.setDeleted(true);
        }
        return new ResponseEntity<Message>(messageRepo.save(message), HttpStatus.OK);
    }

    @Override
    public void makeReport(Long id) {
        Message message= messageRepo.findById(id).orElse(null);
        if(message!=null){
            message.setReportCount(message.getReportCount()+1);
            messageRepo.save(message);
        }
    }

    @Override
    public ResponseEntity<Integer> getReportCount(Long id) {
        Integer count = messageRepo.findById(id).get().getReportCount();
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
}
