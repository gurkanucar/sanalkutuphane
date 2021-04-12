package com.gucarsoft.sanalkutuphane.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Room extends BaseEntity{

    private String roomName;
    private Long personCount;

    private boolean available;
    private boolean readOnly;

    //image



}
