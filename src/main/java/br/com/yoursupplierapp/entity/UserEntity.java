package br.com.yoursupplierapp.entity;

import br.com.yoursupplierapp.utils.CardStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String userName;
    private String email;
    private String password;

    private CardStatus cardStatus;

    @ManyToOne
    private GroupEntity group;

}
