package org.example.ordermanagemnt_service.dtos;

import lombok.Data;

@Data
public class OrderDetail {
    private long productId;
    private int quantity;
}
