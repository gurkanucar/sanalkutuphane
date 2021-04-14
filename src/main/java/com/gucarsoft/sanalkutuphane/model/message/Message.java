package com.gucarsoft.sanalkutuphane.model.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gucarsoft.sanalkutuphane.model.BaseEntity;
import com.gucarsoft.sanalkutuphane.model.FileModel;
import com.gucarsoft.sanalkutuphane.model.Room;
import com.gucarsoft.sanalkutuphane.model.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Message extends BaseEntity {

    private String message;

    @OneToOne
    private FileModel file;

    @ManyToOne
    private User user;

    private int reportCount;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", file=" + file +
                ", user=" + user +
                ", reportCount=" + reportCount +
                ", messageType=" + messageType +
                ", room=" + room +
                '}';
    }
}
