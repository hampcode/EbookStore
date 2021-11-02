package com.example.m3.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
@Entity
public class Order  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name="date_created", nullable = false, updatable = false)
    private LocalDate dateCreated;

    @Column(name="total", nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name="user_id", updatable = false, foreignKey = @ForeignKey(name = "FK_order_user"))
    private User user;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDetail> items;

}