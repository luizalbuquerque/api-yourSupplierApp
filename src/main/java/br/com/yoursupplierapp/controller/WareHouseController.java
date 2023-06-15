package br.com.yoursupplierapp.controller;

import br.com.yoursupplierapp.dto.WarehouseDTO;
import br.com.yoursupplierapp.entity.ProductEntity;
import br.com.yoursupplierapp.entity.WarehouseEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.WareHouseRepository;
import br.com.yoursupplierapp.service.WareHouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WareHouseController {

    private final WareHouseService wareHouseService;

    private final WareHouseRepository wareHouseRepository;

    public WareHouseController(WareHouseService wareHouseService, WareHouseRepository wareHouseRepository) {
        this.wareHouseService = wareHouseService;
        this.wareHouseRepository = wareHouseRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody WarehouseDTO warehouseDTO) {
        try {
            wareHouseService.createWareHouse(warehouseDTO);
            return ResponseEntity.ok("Warehouse created successfully");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error creating warehouse: " + e.getMessage());
        }
    }

    @GetMapping("/{idWarehouse}")
    public ResponseEntity<?> findById(@PathVariable Long idWarehouse) {
        try {
            return wareHouseService.findWarehouseById(idWarehouse);
            //return ResponseEntity.ok(clientService.findUserById(id));  -> Apresenta headers, boddy, status code..
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Erro ao buscar cliente: " + e.getMessage());
        }
    }



}
