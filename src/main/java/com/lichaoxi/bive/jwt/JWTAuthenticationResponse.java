package com.lichaoxi.bive.jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class JWTAuthenticationResponse implements Serializable {

    private String token;

    public JWTAuthenticationResponse(String token) {
        this.token = token;
    }
}
