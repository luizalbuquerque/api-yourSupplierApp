package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.dto.UserDTO;
import br.com.yoursupplierapp.dto.WarehouseDTO;
import br.com.yoursupplierapp.entity.UserEntity;
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

    public ResponseEntity<String> updateWarehouseById(WarehouseDTO warehouseDTO, Long id) {
        try {
            WarehouseEntity warehouseEntity = wareHouseRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("User id number: " + id + " not found in system!"));

            // Update customer fields based on dto data
            warehouseEntity.setName(warehouseDTO.getName());
            warehouseEntity.setCapacity(warehouseDTO.getCapacity());
            warehouseEntity.setAddress(warehouseDTO.getAddress());
            // Saving changes on the database
            wareHouseRepository.save(warehouseEntity);

            return ResponseEntity.ok("Warehouse updated successfully");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error updating client: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<WarehouseEntity> findWarehouseById(Long id) {
        try {
            Optional<WarehouseEntity> clientOptional = wareHouseRepository.findById(id);
            if (!clientOptional.isPresent()) {
                throw new BusinessException("Warehouse with id: " + id + " was not found in the system!");
            }
            WarehouseEntity warehouseEntity = clientOptional.get();
            return ResponseEntity.ok(warehouseEntity);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Warehouse with id: " + id + " was not found in the system!");
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if (wareHouseRepository.existsById(id)) {
            wareHouseRepository.deleteById(id);
            return ResponseEntity.ok("Cliente removido com sucesso");
        } else {
            throw new BusinessException("Cliente com número de ID: " + id + " não foi encontrado no sistema");
        }
    }

}
