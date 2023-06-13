package br.com.yoursupplierapp.dto;

import br.com.yoursupplierapp.entity.GroupEntity;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class UserDTO {

    private String userName;
    private String email;
    private String password;

    @ManyToOne
    private GroupEntity group;

}

