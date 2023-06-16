package br.com.yoursupplierapp.service;

import br.com.yoursupplierapp.dto.WarehouseDTO;
import br.com.yoursupplierapp.entity.WarehouseEntity;
import org.springframework.http.ResponseEntity;

public interface WareHouseService {

    void createWareHouse(WarehouseDTO warehouseDTO);

    ResponseEntity<WarehouseEntity> findWarehouseById(Long id);

    ResponseEntity<String> updateWarehouseById(WarehouseDTO warehouseDTO, Long id);

    ResponseEntity<String>  deleteById(Long id);

//    ResponseEntity<WarehouseEntity> findProductByName(ProductEntity products);


}
