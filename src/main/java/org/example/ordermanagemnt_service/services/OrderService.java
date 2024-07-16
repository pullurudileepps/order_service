package org.example.ordermanagemnt_service.services;

import org.example.ordermanagemnt_service.dtos.OrderDetail;
import org.example.ordermanagemnt_service.exceptions.OrderNotFoundException;
import org.example.ordermanagemnt_service.exceptions.ProductNotAvailableException;
import org.example.ordermanagemnt_service.models.Order;
import org.example.ordermanagemnt_service.models.OrderProduct;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(long orderId) throws OrderNotFoundException;

    Order createOrder(long userId, List<OrderDetail> orderDetails, String token) throws ProductNotAvailableException;

    void deleteOrder(long orderId) throws OrderNotFoundException;

    List<OrderProduct> createOrderProducts(List<OrderDetail> orderDetails);

    void updateProductQuantity(List<OrderDetail> orderDetails, String token);

    List<Long> getTrendingProductIds();
}
