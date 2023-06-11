package br.com.yoursupplierapp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "custumer")
public class CustumerEntity extends UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustumer;
}
