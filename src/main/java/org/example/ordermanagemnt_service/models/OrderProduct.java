package org.example.ordermanagemnt_service.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class OrderProduct extends BaseModel {
    private long productId;
    private int quantity;
}
