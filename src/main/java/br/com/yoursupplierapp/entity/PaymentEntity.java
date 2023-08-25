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

    @Enumerated(EnumType.STRING) // Especificar que paymentConstant é uma enumeração
    private PaymentConstant paymentConstant;

    @Column(name = "num_card")
    private String numCard;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "value")
    private Double paymentValue;

    @OneToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;
}
