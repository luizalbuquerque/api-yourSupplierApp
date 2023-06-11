package br.com.yoursupplierapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class RoleEntity {

        @Id
        private Long id;

        private String name;
}
