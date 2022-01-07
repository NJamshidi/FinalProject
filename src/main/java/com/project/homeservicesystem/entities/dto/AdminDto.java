package com.project.homeservicesystem.entities.dto;

import lombok.Data;

import javax.persistence.Column;
@Data
public class AdminDto {
    private int id;
    private String userName;
    private String passWord;

    @Override
    public String toString() {
        return "AdminDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
