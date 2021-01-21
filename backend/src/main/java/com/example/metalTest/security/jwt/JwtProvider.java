package com.example.metalTest.security.jwt;

import com.example.metalTest.usuarios.usuarioCredencial.controller.UsuarioCredencialController;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value(("$jwt.secret"))
    private String secret;
    @Value(("$jwt.expiration"))
    private String expiracion;

    public String generateToken(Authentication authentication){
        UsuarioCredencialController usrCredController = (UsuarioCredencialController) authentication.getPrincipal();

        return Jwts.builder().setSubject(usrCredController.getNombre_usuario())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Integer.valueOf(expiracion)*1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException a ){
            logger.error("Token mal formado");
        }catch (UnsupportedJwtException b){
            logger.error("Token no soportado");
        }catch (ExpiredJwtException a){
            logger.error("Token expirado");
        }catch (IllegalArgumentException a){
            logger.error("Token vacio");
        }catch (SignatureException a){
            logger.error("fail en la firma");
        }
        return false;
    }



}
