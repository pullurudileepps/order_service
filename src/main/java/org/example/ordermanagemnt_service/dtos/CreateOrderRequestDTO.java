package org.example.ordermanagemnt_service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDTO {
    private long userId;
    private List<OrderDetail> orderDetails;
}