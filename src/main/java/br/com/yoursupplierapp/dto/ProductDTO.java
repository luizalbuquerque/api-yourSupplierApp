package br.com.yoursupplierapp.dto;

import br.com.yoursupplierapp.entity.OrderEntity;
import br.com.yoursupplierapp.entity.WarehouseEntity;
import lombok.Data;

@Data
public class ProductDTO {
    private Long idProduct;
    private String name;
    private double price;
    private WarehouseEntity warehouse;
    private OrderEntity order;

}
