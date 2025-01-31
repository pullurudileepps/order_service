package org.example.ordermanagemnt_service.controllers;

import org.example.ordermanagemnt_service.dtos.GetOrderResponseDTO;
import org.example.ordermanagemnt_service.dtos.ResponseStatus;
import org.example.ordermanagemnt_service.models.Order;
import org.example.ordermanagemnt_service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.ordermanagemnt_service.dtos.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public GetOrderResponseDTO getOrderById(@PathVariable("id") long orderId) {
        GetOrderResponseDTO responseDTO = new GetOrderResponseDTO();
        try {
            Order order = this.orderService.getOrderById(orderId);
            responseDTO.setOrder(order);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    @PostMapping
    public CreateOrderResponseDTO createOrder(@RequestBody CreateOrderRequestDTO requestDTO, @RequestHeader("Auth") String token) {
        long userId = requestDTO.getUserId();
        List<OrderDetail> orderDetails = requestDTO.getOrderDetails();
        CreateOrderResponseDTO responseDTO = new CreateOrderResponseDTO();
        try {
            Order order = this.orderService.createOrder(userId, orderDetails, token);
            this.orderService.updateProductQuantity(orderDetails, token);
            responseDTO.setOrder(order);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return  responseDTO;
    }

    @DeleteMapping("/{id}")
    public DeleteOrderResponseDTO deleteOrder(@PathVariable("id") long orderId) {
        DeleteOrderResponseDTO responseDTO = new DeleteOrderResponseDTO();
        try {
            this.orderService.deleteOrder(orderId);
            responseDTO.setMessage("Order with ID: " + orderId + ", has been DELETED");
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDTO.setMessage(e.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }

    @GetMapping("/{id}/amount")
    public ResponseEntity<Double> getOrderAmount(@PathVariable("id") long orderId) {
        try {
            Order order = this.orderService.getOrderById(orderId);
            if(order == null) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(order.getTotalAmount(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/trending")
    public List<Long> getTrendingProductIds() {
        return this.orderService.getTrendingProductIds();
    }
}