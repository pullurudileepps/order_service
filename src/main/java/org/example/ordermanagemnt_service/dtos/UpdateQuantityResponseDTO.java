package org.example.ordermanagemnt_service.dtos;

import lombok.Data;

@Data
public class UpdateQuantityResponseDTO {
    private ProductDTO productDTO;
    private String message;
    private ResponseStatus responseStatus;
}
