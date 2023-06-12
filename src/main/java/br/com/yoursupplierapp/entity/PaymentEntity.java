package br.com.yoursupplierapp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @OneToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

}
