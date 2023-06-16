package br.com.yoursupplierapp.repository;

import br.com.yoursupplierapp.entity.ProductEntity;
import br.com.yoursupplierapp.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WareHouseRepository extends JpaRepository<WarehouseEntity, Long> {

//      Optional<WarehouseEntity> findProductByName(ProductEntity product);
//
        Optional<WarehouseEntity>findById(Long id);
//
//      Optional<WarehouseEntity> findByName(String name);
}
