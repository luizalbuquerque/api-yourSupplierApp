package br.com.yoursupplierapp.entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "promoters")
public class PromoterEntity  extends UserEntity {

}
