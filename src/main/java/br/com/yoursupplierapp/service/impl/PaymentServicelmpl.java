package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.dto.PaymentDTO;
import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.PaymentRepository;
import br.com.yoursupplierapp.service.PaymentService;
import br.com.yoursupplierapp.utils.PaymentConstant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        isExistentPayment(paymentRepository, paymentDTO);

        if (paymentDTO.getNumCard().length() != 8) {
            throw new BusinessException("Card number must have 8 characters.");
        } else if (paymentDTO.getCvv().length() != 3) {
            throw new BusinessException("Card cvv must have 3 characters.");
        } else if (paymentDTO.getExpirationDate().length() != 5) {
            throw new BusinessException("Card date must have 5 characters.");
        } else if (paymentDTO.getPaymentValue() == 0.0) {
            throw new BusinessException("Invalid payment value.");
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/yy");
        try {
            YearMonth expirationYearMonth = YearMonth.parse(paymentDTO.getExpirationDate(), dateFormatter);
            if (expirationYearMonth.isBefore(YearMonth.now())) {
                throw new BusinessException("Card has expired.");
            }
        } catch (DateTimeParseException e) {
            throw new BusinessException("Invalid expiration date format. Please use MM/yy format.");
        }

        switch (paymentDTO.getPaymentConstant()) {
            case PIX:
                if (paymentDTO.getPaymentValue() > 3000.0) {
                    throw new BusinessException("Payment value cannot exceed $3000.00 for PIX payments.");
                }
                break;
            case BOLETO:
                if (paymentDTO.getPaymentValue() > 5000.0) {
                    throw new BusinessException("Payment value cannot exceed $5000.00 for bole payments.");
                }
                break;
            case CREDITO:
                if (paymentDTO.getPaymentValue() > 8000.0) {
                    throw new BusinessException("Payment value cannot exceed $8000.00 for bole payments.");
                }
                break;
            case DEBITO:
                if (paymentDTO.getPaymentValue() > 10000.0) {
                    throw new BusinessException("Payment value cannot exceed $10.000.00 for bole payments.");
                }
                break;
            default:
                // Handle the default case if needed
                break;
        }

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
        if ((paymentDTO.getNumCard().length()) != 8) {
            throw new BusinessException("Card number must have 8 characters.");
        } else if (paymentDTO.getCvv().length() != 3) {
            throw new BusinessException("Card cvv must have 3 characters.");
        } else if (paymentDTO.getExpirationDate().length() != 5) {
            throw new BusinessException("Card cvv must have 5 characters.");
        } else if (paymentDTO.getPaymentValue() == 00.0) {
            throw new BusinessException("Invalid payment value.");
        } else if (paymentDTO.getPaymentConstant() == PaymentConstant.PIX  && paymentDTO.getPaymentValue() > 3000.0) {
            throw new BusinessException("Payment value cannot exceed $3000.00 for PIX payments.");

        } else if (paymentDTO.getPaymentConstant() == PaymentConstant.BOLETO  && paymentDTO.getPaymentValue() > 5000.0) {
            throw new BusinessException("Payment value cannot exceed $5000.00 for bolet payments.");

        } else if (paymentDTO.getPaymentConstant() == PaymentConstant.CREDITO  && paymentDTO.getPaymentValue() > 8000.0) {
            throw new BusinessException("Payment value cannot exceed $8000.00 for bolet payments.");

        }else if(paymentDTO.getPaymentConstant() == PaymentConstant.DEBITO  && paymentDTO.getPaymentValue() > 10000.0){
            throw new BusinessException("Payment value cannot exceed $10.000.00 for bolet payments.");

        }else if (paymentDTO.getPaymentValue() > 999999.99) {
            throw new BusinessException("Payment value cannot exceed 999.999.99.");
        }

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


