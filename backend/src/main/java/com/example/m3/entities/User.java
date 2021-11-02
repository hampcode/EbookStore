package com.example.m3.entities;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="username",length = 30,nullable = false)
    private String username;
    @Column(name="password",length = 150,nullable = false)
    private String password;
}