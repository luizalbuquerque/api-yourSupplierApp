package br.com.yoursupplierapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne
    private CustumerEntity custumerEntity;

    @ManyToOne
    private SupplierEntity supplierEntity;

    @ManyToOne
    private PromoterEntity promoterEntity;

    @OneToOne(mappedBy = "order")
    private PaymentEntity paymentEntity;

}
