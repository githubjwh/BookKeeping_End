package com.example.demo.Service;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenService {
	
	private static final String token_secret="q3nr98of";
	private static final long exist_time = 30*60*1000;
	
	public static String createToken(String username){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + exist_time);
            token = JWT.create()
                    .withIssuer("system")
                    .withClaim("username", username)
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(token_secret));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
	
	public static boolean verify(String token) {
		try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(token_secret)).withIssuer("system").build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
	}
}
