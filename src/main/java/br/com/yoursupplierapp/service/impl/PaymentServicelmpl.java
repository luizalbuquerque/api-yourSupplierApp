package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServicelmpl {

 private final PaymentRepository paymentRepository;

    public PaymentServicelmpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;

    }

    public ResponseEntity<PaymentEntity> findPaymentByNumCard(String numCard){

    }
}
