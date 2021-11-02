package com.example.m3.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false, length = 70)
    private String title;

    @Column(name="description", nullable = false, length = 255)
    private String description;

    @Column(name="price", nullable = false)
    private Double price;
}
