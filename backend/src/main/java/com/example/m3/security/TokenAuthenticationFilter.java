package com.example.m3.security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.m3.entities.User;
import com.example.m3.exception.ResourceNotFoundException;
import com.example.m3.repositories.UserRepository;
import com.example.m3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && userService.validateToken(jwt)) {
                String username = userService.getUsernameFromToken(jwt);
                User user = userRepo.findByUsername(username)
                        .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario"));

                UserPrincipal principal = UserPrincipal.create(user);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(principal,
                                null, principal.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            //log.error("Error al autenticar al usuario", e);
        }
        filterChain.doFilter(request, response);
    }

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}