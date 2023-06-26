package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.dto.ProductDTO;

import br.com.yoursupplierapp.entity.ProductEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.ProductRepository;
import br.com.yoursupplierapp.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.yoursupplierapp.utils.ConstantUtils.DUPLICATED_USER;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProducts(ProductDTO productDTO) {
        try {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(productDTO.getName());
            productEntity.setOrder(productDTO.getOrder());
            productEntity.setPrice(productDTO.getPrice());
            productRepository.save(productEntity);
            //If there is already a user with this userName and this Email.
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(DUPLICATED_USER);
        }
    }

    @Override
    public ResponseEntity<ProductEntity> findProductById(Long id) {
        try {
            Optional<ProductEntity> clientOptional = productRepository.findById(id);
            if (!clientOptional.isPresent()) {
                throw new BusinessException("Product with ID number: " + id + " was not found in the system!");
            }
            ProductEntity product = clientOptional.get();
            return ResponseEntity.ok(product);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Product with ID number: " + id + " was not found in the system!");
        }
    }

    public ResponseEntity<String> updateProductById(ProductDTO productDTO, Long id) {
        try {
            ProductEntity existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Product id number: " + id + " not found in system!"));

            // Update customer fields based on dto data
            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());
            // Saving changes on the database
            productRepository.save(existingProduct);

            return ResponseEntity.ok("Product updated successfully");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error updating product: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Product removed successfully");
        } else {
            throw new BusinessException("Product id number: " + id + " not found in system!");
        }
    }

}
