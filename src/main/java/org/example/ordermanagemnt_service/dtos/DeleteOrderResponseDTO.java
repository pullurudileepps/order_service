package org.example.ordermanagemnt_service.dtos;

import lombok.Data;

@Data
public class DeleteOrderResponseDTO {
    private String message;
    private ResponseStatus responseStatus;
}
