package br.com.yoursupplierapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "warehouses")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWarehouse;

    @OneToMany(mappedBy = "warehouse")
    private List<ProductEntity> products;


}
