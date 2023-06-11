package br.com.yoursupplierapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne
    private CustumerEntity custumerEntity;

    @OneToOne(mappedBy = "order")
    private PaymentEntity paymentEntity;

}
