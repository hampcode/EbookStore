package com.example.m3.validators;

import com.example.m3.entities.Order;
import com.example.m3.entities.OrderDetail;
import com.example.m3.exception.IncorrectResourceRequestException;

public class OrderValidator {
    public static void  validate(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IncorrectResourceRequestException("Las líneas son requeridas");
        }
        for(OrderDetail line: order.getItems()) {
            if(line.getBook() == null || line.getBook().getId() == null) {
                throw new IncorrectResourceRequestException("El libro es requerido");
            }

            if(line.getQuantity() == null){
                throw new IncorrectResourceRequestException("La cantidad es requerido");
            }
            if(line.getQuantity() <= 0) {
                throw new IncorrectResourceRequestException("La cantidad es incorrecto. Debe ser un valor positivo mayor de cero");
            }
        }
    }
}