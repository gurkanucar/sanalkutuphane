package com.gucarsoft.sanalkutuphane.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class FileModel extends BaseEntity{

    private String name;
    private String url;

}
