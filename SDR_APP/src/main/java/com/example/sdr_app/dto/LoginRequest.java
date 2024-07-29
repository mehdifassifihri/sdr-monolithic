package com.example.sdr_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
}