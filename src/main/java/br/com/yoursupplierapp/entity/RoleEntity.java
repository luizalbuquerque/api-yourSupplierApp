package br.com.yoursupplierapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    private String roleName;

//    @ManyToMany(mappedBy = "roles")
//    private List<GroupEntity> groupList;
}
