package com.example.m3.service;

import com.example.m3.entities.Book;
import com.example.m3.entities.Order;
import com.example.m3.entities.OrderDetail;
import com.example.m3.entities.User;
import com.example.m3.exception.GeneralServiceException;
import com.example.m3.exception.IncorrectResourceRequestException;
import com.example.m3.exception.ResourceNotFoundException;
import com.example.m3.repositories.BookRepository;
import com.example.m3.repositories.OrderRepository;
import com.example.m3.security.UserPrincipal;
import com.example.m3.validators.OrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository=bookRepository;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public Order createOrder(Order order) {
        try {
            OrderValidator.validate(order);

            User user= UserPrincipal.getCurrentUser();

            double total = 0;
            for(OrderDetail item : order.getItems()) {
                Book book = bookRepository.findById(item.getBook().getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No existe el producto " + item.getBook().getId()));

                item.setPrice(book.getPrice());
                item.setTotal(book.getPrice() * item.getQuantity());
                total += item.getTotal();
            }
            order.setTotal(total);
            order.getItems().forEach(line -> line.setOrder(order));

            order.setUser(user);
            order.setDateCreated(LocalDate.now());

            return orderRepository.save(order);
        } catch (IncorrectResourceRequestException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }


    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La orden no existe"));
    }
}