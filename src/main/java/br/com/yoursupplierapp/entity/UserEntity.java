package br.com.yoursupplierapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String nameUser;
    private String email;
    private String password;

    @ManyToOne
    private GroupEntity group;

}
