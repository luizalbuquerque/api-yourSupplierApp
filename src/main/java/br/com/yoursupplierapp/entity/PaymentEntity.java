package br.com.yoursupplierapp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
