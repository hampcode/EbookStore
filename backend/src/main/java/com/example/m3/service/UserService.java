package com.example.m3.service;

import com.example.m3.converters.UserConverter;
import com.example.m3.dto.LoginRequestDto;
import com.example.m3.dto.LoginResponseDto;
import com.example.m3.entities.User;
import com.example.m3.exception.GeneralServiceException;
import com.example.m3.exception.IncorrectResourceRequestException;
import com.example.m3.exception.ResourceNotFoundException;
import com.example.m3.repositories.UserRepository;
import com.example.m3.validators.UserValidator;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Value("${jwt.password}")
    private String jwtSecret;

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(User user) {
        try {
            UserValidator.validate(user);
            User existUser=userRepository.findByUsername(user.getUsername())
                    .orElse(null);
            if(existUser!=null)
                throw new IncorrectResourceRequestException("El nombre usuario ya existe");

            String encoder=passwordEncoder.encode(user.getPassword());
            user.setPassword(encoder);

            return userRepository.save(user);
        } catch (IncorrectResourceRequestException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public List<User> findAll(){
        try {
            return userRepository.findAll();
        } catch (IncorrectResourceRequestException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public LoginResponseDto login(LoginRequestDto request){
        try {
            User user=userRepository.findByUsername(request.getUsername())
                    .orElseThrow(()->new IncorrectResourceRequestException("Usuario o password incorrecto"));

            if(!passwordEncoder.matches(request.getPassword(),user.getPassword()))
                throw new IncorrectResourceRequestException("Usuario o password incorrectos");

            String token =createToken(user);

            return new LoginResponseDto(userConverter.fromEntity(user),token);

        } catch (IncorrectResourceRequestException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public String createToken(User user){
        Date now =new Date();
        Date expiryDate=new Date(now.getTime()+ (1000*60*60*24));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (UnsupportedJwtException e) {
            log.error("JWT in a particular format/configuration that does not match the format expected");
        }catch (MalformedJwtException e) {
            log.error(" JWT was not correctly constructed and should be rejected");
        }catch (SignatureException e) {
            log.error("Signature or verifying an existing signature of a JWT failed");
        }catch (ExpiredJwtException e) {
            log.error("JWT was accepted after it expired and must be rejected");
        }
        return false;
    }

    public String getUsernameFromToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            throw new IncorrectResourceRequestException("Invalid Token");
        }
    }
}