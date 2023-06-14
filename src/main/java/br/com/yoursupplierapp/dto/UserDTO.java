package br.com.yoursupplierapp.dto;

import br.com.yoursupplierapp.entity.GroupEntity;
import br.com.yoursupplierapp.utils.CardStatus;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.util.List;

@Data
public class UserDTO {

    private String userName;
    private String email;
    private String password;

    private CardStatus cardStatus;

    @ManyToOne
    private List<GroupEntity> groups;

}

