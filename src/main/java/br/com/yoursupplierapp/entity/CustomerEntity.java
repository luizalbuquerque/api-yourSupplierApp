package br.com.yoursupplierapp.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity {

}
