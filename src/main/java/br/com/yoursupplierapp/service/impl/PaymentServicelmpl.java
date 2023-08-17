package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.dto.PaymentDTO;
import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.PaymentRepository;
import br.com.yoursupplierapp.service.PaymentService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.yoursupplierapp.utils.ConstantUtils.DUPLICATED_PAYMENT;

@Service
public class PaymentServicelmpl implements PaymentService {

 private final PaymentRepository paymentRepository;

    public PaymentServicelmpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;

    }

    @Override
    public void createPayment(PaymentDTO paymentDTO) {
        isExistentPayment(paymentRepository, paymentDTO);  // Verifique a existência do pagamento

        try {
            PaymentEntity paymentEntity = new PaymentEntity();
            paymentEntity.setNumCard(paymentDTO.getNumCard());
            paymentEntity.setCvv(paymentDTO.getCvv());
            paymentEntity.setPaymentValue(paymentDTO.getPaymentValue());
            paymentEntity.setExpirationDate(paymentDTO.getExpirationDate());
            // Defina o tipo de pagamento com base na PaymentConstant do DTO
            paymentEntity.setPaymentConstant(paymentDTO.getPaymentConstant());

            paymentRepository.save(paymentEntity);  // Salve o pagamento no banco de dados
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(DUPLICATED_PAYMENT);

            }
        }



    @Override
    public void isExistentPayment(PaymentRepository paymentRepository, PaymentDTO paymentDTO) {

        if (paymentRepository.findPaymentByNumCard(paymentDTO.getNumCard()).isPresent()) {
            throw new BusinessException("Card number " + paymentDTO.getNumCard() + " already registered in the system!");
        }
    }

    @Override
    public ResponseEntity<String> updatePayment(PaymentDTO paymentDTO, Long id) {
        try {
            PaymentEntity existingPayment = paymentRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("User id number: " + id + " not found in system!"));

            // Update customer fields based on dto data
            existingPayment.setNumCard(paymentDTO.getNumCard());
            existingPayment.setPaymentValue(paymentDTO.getPaymentValue());
            existingPayment.setExpirationDate(paymentDTO.getExpirationDate());
            existingPayment.setCvv(paymentDTO.getCvv());


            paymentRepository.save(existingPayment);

            return ResponseEntity.ok("payment updated successfully");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error updating payment: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if(paymentRepository.existsById(id)){
            paymentRepository.deleteById(id);
            return ResponseEntity.ok("payment removed successfully");
        } else {
            throw new BusinessException("Payment id number " + id + "not found in system");
        }
    }

    @Override
    public ResponseEntity<PaymentEntity> findPaymentById(Long idPayment) {
        Optional<PaymentEntity> paymentOptional = paymentRepository.findById(idPayment);
        if (paymentOptional.isPresent()) {
            PaymentEntity payment = paymentOptional.get();
            return ResponseEntity.ok(payment);
        } else {
            throw new BusinessException("Payment with ID: " + idPayment + " not found in the system!");
        }
    }




}


