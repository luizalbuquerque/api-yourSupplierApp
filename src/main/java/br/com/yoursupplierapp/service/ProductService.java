package br.com.yoursupplierapp.service;

import br.com.yoursupplierapp.dto.ProductDTO;
import br.com.yoursupplierapp.dto.UserDTO;
import br.com.yoursupplierapp.entity.ProductEntity;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    void createProducts(ProductDTO productDTO);

    ResponseEntity<ProductEntity> findProductById(Long id);

    ResponseEntity<String>  deleteById(Long id);

    ResponseEntity<String> updateProductById(ProductDTO productDTO, Long id);

}
