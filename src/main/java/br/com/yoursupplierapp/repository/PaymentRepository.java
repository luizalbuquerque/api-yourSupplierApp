package br.com.yoursupplierapp.repository;

import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.utils.PaymentConstant;

import java.util.Optional;

public interface PaymentRepository {

    Optional<PaymentEntity> findPaymentBy(PaymentConstant paymentConstant);

}
