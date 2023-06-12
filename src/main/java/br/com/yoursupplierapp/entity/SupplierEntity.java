package br.com.yoursupplierapp.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "suppliers")
public class SupplierEntity extends UserEntity {
}
