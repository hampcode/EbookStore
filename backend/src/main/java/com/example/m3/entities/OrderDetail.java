package com.example.m3.entities;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_details")
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="price", nullable = false)
    private Double price;

    @Column(name="quantity", nullable = false)
    private Double quantity;

    @Column(name="total", nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "FK_order_order_details"))
    private Order order;


    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "FK_book_order_details"))
    private Book book;

}