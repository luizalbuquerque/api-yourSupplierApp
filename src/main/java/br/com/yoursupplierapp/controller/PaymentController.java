package br.com.yoursupplierapp.controller;

import br.com.yoursupplierapp.dto.PaymentDTO;
import br.com.yoursupplierapp.dto.UserDTO;
import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.entity.UserEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.PaymentRepository;
import br.com.yoursupplierapp.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentRepository paymentRepository;


    public PaymentController(PaymentService paymentService, PaymentRepository paymentRepository) {
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(
            @RequestBody PaymentDTO paymentDTO) {
        try {
            paymentService.createPayment(paymentDTO);
            return ResponseEntity.ok("payment created with success");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error creating payment: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentEntity>> list() {
        List<PaymentEntity> payment = paymentRepository.findAll();
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Long id) {
        try {
            return paymentService.findPaymentById(id);
            //return ResponseEntity.ok(clientService.findUserById(id)); -> Displays headers, body, status code...
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error finding client: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(
            @RequestBody PaymentDTO paymentDTO,
            @PathVariable("id") Long id) {
        return paymentService.updatePayment(paymentDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable("id") Long id) {
        try {
            return paymentService.deleteById(id);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error deleting client: " + e.getMessage());
        }
    }

}
