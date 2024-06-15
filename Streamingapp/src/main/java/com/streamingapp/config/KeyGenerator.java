package com.streamingapp.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class KeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(base64Key);
    }
}

