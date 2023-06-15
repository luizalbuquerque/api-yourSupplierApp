package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.dto.WarehouseDTO;
import br.com.yoursupplierapp.entity.WarehouseEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.WareHouseRepository;
import br.com.yoursupplierapp.service.WareHouseService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class WareHouseServiceImpl implements WareHouseService {

    private final WareHouseRepository wareHouseRepository;

    public WareHouseServiceImpl(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }

    @Override
    public void createWareHouse(WarehouseDTO warehouseDTO) {
        try {
            WarehouseEntity warehouseEntity = new WarehouseEntity();
            warehouseEntity.setName(warehouseDTO.getName());
            warehouseEntity.setAddress(warehouseDTO.getAddress());
            warehouseEntity.setCapacity(warehouseDTO.getCapacity());
            wareHouseRepository.save(warehouseEntity);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Cannot create warehouse");
        }
    }

    @Override
    public ResponseEntity<WarehouseEntity> findWarehouseById(Long idWarehouse) {
        try {
            Optional<WarehouseEntity> clientOptional = wareHouseRepository.findById(idWarehouse);
            if (!clientOptional.isPresent()) {
                throw new BusinessException("Warehouse with id: " + idWarehouse + " was not found in the system!");
            }
            WarehouseEntity warehouseEntity = clientOptional.get();
            return ResponseEntity.ok(warehouseEntity);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Warehouse with id: " + idWarehouse + " was not found in the system!");
        }
    }



}
