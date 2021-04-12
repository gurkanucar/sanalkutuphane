package com.gucarsoft.sanalkutuphane.model;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@Data
@Entity
@Where(clause = "deleted=false")
public class Room extends BaseEntity{

    private String roomName;
    private Long personCount;

    private boolean available;
    private boolean readOnly;

    //image



}
