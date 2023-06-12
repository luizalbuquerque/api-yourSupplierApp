package br.com.yoursupplierapp.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "custumers")
public class CustumerEntity extends UserEntity {

}
