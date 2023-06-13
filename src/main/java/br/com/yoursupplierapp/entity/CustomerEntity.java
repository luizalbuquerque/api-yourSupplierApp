package br.com.yoursupplierapp.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity extends UserEntity {

}
