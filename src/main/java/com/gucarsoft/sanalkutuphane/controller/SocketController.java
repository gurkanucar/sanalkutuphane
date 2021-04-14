package com.gucarsoft.sanalkutuphane.controller;

import com.gucarsoft.sanalkutuphane.model.Room;
import com.gucarsoft.sanalkutuphane.model.message.Message;
import com.gucarsoft.sanalkutuphane.model.user.User;
import com.gucarsoft.sanalkutuphane.repository.MessageRepository;
import com.gucarsoft.sanalkutuphane.repository.RoomRepository;
import com.gucarsoft.sanalkutuphane.repository.UserRepository;
import com.gucarsoft.sanalkutuphane.service.MessageService;
import com.gucarsoft.sanalkutuphane.service.RoomService;
import com.gucarsoft.sanalkutuphane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin("http://localhost:8080")
public class SocketController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    MessageService messageService;

    @Autowired
    RoomService roomService;

/*
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    MessageService messageService;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @MessageMapping("/chat")
    //@SendTo("/topic")
    //@SendToUser()
    public void chatEndPoint(@Payload Message message){
        System.out.println(message.toString());
        messageService.create(message);
        simpMessagingTemplate.convertAndSend(message);
        // spesifik bi kanala yolla
        //simpMessagingTemplate.convertAndSend("/topic2",message);
    }
*/

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Message register(@Payload Message chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        User user= userRepo.findByUsername(chatMessage.getUser().getUsername());
        chatMessage.setUser(user);
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        User user= userRepo.findByUsername(chatMessage.getUser().getUsername());
        chatMessage.setUser(user);
        Room room = roomRepo.findById(chatMessage.getRoom().getId()).get();
        chatMessage.setRoom(room);
        messageRepo.save(chatMessage);
        return chatMessage;
    }




}
