package br.com.yoursupplierapp.repository;

import br.com.yoursupplierapp.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

//        Optional<ProductEntity> findProductByNameOfProduct(String name);

//        Optional<ProductEntity> findByOrder(String userName);
}