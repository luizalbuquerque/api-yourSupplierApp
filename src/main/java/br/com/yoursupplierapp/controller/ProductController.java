package br.com.yoursupplierapp.controller;

import br.com.yoursupplierapp.dto.ProductDTO;
import br.com.yoursupplierapp.entity.ProductEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.ProductRepository;
import br.com.yoursupplierapp.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(
            @RequestBody ProductDTO productDTO) {
        try {
            productService.createProducts(productDTO);
            return ResponseEntity.ok("Product created with success");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error creating product: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> list() {
        Pageable pageable = PageRequest.of(2, 25); // Returns only the first page with 1000 results

        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        List<ProductEntity> products = productPage.getContent();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return productService.findProductById(id);
            //return ResponseEntity.ok(clientService.findUserById(id));  -> Apresenta headers, boddy, status code..
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error finding product: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(
            @RequestBody ProductDTO productDTO,
            @PathVariable("id") Long id) {
        return productService.updateProductById(productDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable("id") Long id) {
        try {
            return productService.deleteById(id);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error deleting client: " + e.getMessage());
        }
    }

}
