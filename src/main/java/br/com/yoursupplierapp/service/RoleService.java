package br.com.yoursupplierapp.service;

import br.com.yoursupplierapp.dto.RoleDTO;
import br.com.yoursupplierapp.entity.RoleEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.RoleRepository;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface RoleService {

    void createRole(RoleDTO roleDTO);

    void isExistentRole(RoleRepository roleRepository, RoleDTO roleDTO) throws BusinessException;

    ResponseEntity<RoleEntity> findRoleById(Long id);

    ResponseEntity<String> updateRoleById(RoleDTO roleDTO, Long id);
    ResponseEntity<String>  deleteById(Long id);

}
