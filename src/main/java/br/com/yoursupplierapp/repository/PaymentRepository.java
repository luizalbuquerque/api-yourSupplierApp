package br.com.yoursupplierapp.repository;

import br.com.yoursupplierapp.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

     Optional<PaymentEntity> findPaymentByNumCard(String numCard);


    }


