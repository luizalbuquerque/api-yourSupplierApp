package br.com.yoursupplierapp.dto;

import br.com.yoursupplierapp.entity.ProductEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WarehouseDTO implements Serializable {

    private Long idWarehouse;
    private String name;
    private String address;
    private int capacity;
    private List<ProductEntity> products;
}
