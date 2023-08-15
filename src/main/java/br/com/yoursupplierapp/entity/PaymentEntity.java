package br.com.yoursupplierapp.entity;


import br.com.yoursupplierapp.utils.PaymentConstant;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    private PaymentConstant paymentConstant;

    private String numCard;

    private int cvv;

    private String DateValidity;

    @OneToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

}
