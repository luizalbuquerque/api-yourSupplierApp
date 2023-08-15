package br.com.yoursupplierapp.dto;

import br.com.yoursupplierapp.entity.PaymentEntity;
import br.com.yoursupplierapp.utils.PaymentConstant;
import lombok.Data;

import java.util.List;

@Data
public class PaymentDTO {

    private Long idPayment;

    private PaymentConstant paymentConstant;

    private String numCard;

    private int cvv;

    private String DateValidity;

    private List<PaymentEntity> paymentEntityList;
}
