package com.project.homeservicesystem.entities.users;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String userName;
    private String passWord;


}
