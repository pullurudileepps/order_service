package org.example.ordermanagemnt_service.dtos;


import lombok.Data;

@Data
public class Response {
    private Object body;
    private String message;
    private ResponseStatus responseStatus;
}
