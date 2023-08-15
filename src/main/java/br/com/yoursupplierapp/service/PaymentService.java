package br.com.yoursupplierapp.service;

import br.com.yoursupplierapp.dto.PaymentDTO;
import br.com.yoursupplierapp.entity.PaymentEntity;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    ResponseEntity<PaymentEntity> findPaymentById(Long idPayment);

    ResponseEntity<PaymentEntity> findPaymentByNumCard(String numCard);
    void createPayment(PaymentDTO paymentDTO);

    void isExistentPayment(PaymentDTO paymentDTO);


}
