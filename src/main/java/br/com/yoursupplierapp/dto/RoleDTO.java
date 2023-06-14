package br.com.yoursupplierapp.dto;

import br.com.yoursupplierapp.entity.GroupEntity;
import lombok.Data;

import javax.persistence.ManyToMany;
import java.util.List;


@Data
public class RoleDTO {

    private String roleName;

}
