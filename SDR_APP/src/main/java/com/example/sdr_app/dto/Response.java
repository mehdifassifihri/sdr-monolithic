package com.example.sdr_app.dto;

import com.example.sdr_app.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private User user;
    private String accesstoken;



}
