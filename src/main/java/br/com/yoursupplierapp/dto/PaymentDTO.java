package br.com.yoursupplierapp.dto;

import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.utils.PaymentConstant;
import lombok.Data;

import java.util.List;

@Data
public class PaymentDTO {

    private PaymentConstant paymentConstant;

    private String numCard;

    private String cvv;

    private String expirationDate;

    private Double paymentValue;

    private List<PaymentEntity> paymentEntityList;
}
