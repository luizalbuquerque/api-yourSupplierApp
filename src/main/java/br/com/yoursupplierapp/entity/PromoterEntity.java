package br.com.yoursupplierapp.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "promoter")
public class PromoterEntity extends UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPromoter;
}
