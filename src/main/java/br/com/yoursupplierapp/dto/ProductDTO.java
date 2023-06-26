package br.com.yoursupplierapp.dto;

import br.com.yoursupplierapp.entity.CategoryEntity;
import br.com.yoursupplierapp.entity.OrderEntity;
import br.com.yoursupplierapp.entity.WarehouseEntity;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

    private Long idProduct;

    private String name;

    private Double price;

    private Double weight;

    private List<CategoryEntity> categoryEntity;

    private WarehouseEntity warehouse;

    private OrderEntity order;

}
