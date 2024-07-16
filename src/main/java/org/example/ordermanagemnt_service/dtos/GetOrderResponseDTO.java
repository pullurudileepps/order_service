package org.example.ordermanagemnt_service.dtos;


import lombok.Data;
import org.example.ordermanagemnt_service.models.Order;

@Data
public class GetOrderResponseDTO {
    private Order order;
    private String message;
    private ResponseStatus responseStatus;
}
