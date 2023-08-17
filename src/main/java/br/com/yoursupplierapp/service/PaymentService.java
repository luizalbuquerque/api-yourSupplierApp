package br.com.yoursupplierapp.service;

import br.com.yoursupplierapp.dto.PaymentDTO;
import br.com.yoursupplierapp.dto.UserDTO;
import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

    ResponseEntity<PaymentEntity> findPaymentById(Long idPayment);

    void createPayment(PaymentDTO paymentDTO);

    void isExistentPayment(PaymentRepository paymentRepository, PaymentDTO paymentDTO) throws BusinessException;

    ResponseEntity<String> updatePayment(PaymentDTO paymentDTO, Long id);

    ResponseEntity<String> deleteById(Long id);


}
