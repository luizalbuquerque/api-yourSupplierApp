package br.com.yoursupplierapp.service;

import br.com.yoursupplierapp.dto.WarehouseDTO;
import br.com.yoursupplierapp.entity.ProductEntity;
import br.com.yoursupplierapp.entity.WarehouseEntity;
import org.springframework.http.ResponseEntity;

public interface WareHouseService {

    void createWareHouse(WarehouseDTO warehouseDTO);

    ResponseEntity<WarehouseEntity> findWarehouseById(Long idWarehouse);


//    ResponseEntity<WarehouseEntity> findProductByName(ProductEntity products);


}
