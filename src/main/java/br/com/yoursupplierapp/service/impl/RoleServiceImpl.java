package br.com.yoursupplierapp.service.impl;


import br.com.yoursupplierapp.dto.RoleDTO;
import br.com.yoursupplierapp.entity.GroupEntity;
import br.com.yoursupplierapp.entity.RoleEntity;
import br.com.yoursupplierapp.entity.UserEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.RoleRepository;
import br.com.yoursupplierapp.service.RoleService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole(@NotBlank(message = "O nome da role não pode estar em branco") RoleDTO roleDTO) {

        isExistentRole(roleRepository, roleDTO);

        try {
            RoleEntity roleEntity = new RoleEntity();
            if (StringUtils.hasText(roleDTO.getRoleName())) {
                roleEntity.setRoleName(roleDTO.getRoleName());
                roleRepository.save(roleEntity);
            } else {
                throw new IllegalArgumentException("O nome da role não pode ser nulo ou em branco");
            }

        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Erro ao criar usuario: " + e.getMessage());
        }
    }

    @Override
        public void isExistentRole(RoleRepository roleRepository, RoleDTO roleDTO) throws BusinessException {
            if (roleRepository.findRoleByRoleName(roleDTO.getRoleName()).isPresent()) {
                throw new BusinessException("Role name: " + roleDTO.getRoleName() + " already registered in the system!");
            }
        }

    @Override
    public ResponseEntity<RoleEntity> findRoleById(Long id) {
        try {
            Optional<RoleEntity> roleOptional = roleRepository.findById(id);
            if (!roleOptional.isPresent()) {
                throw new BusinessException("Role id number: " + id + " not found in system!");
            }
            RoleEntity role = roleOptional.get();
            return ResponseEntity.ok(role);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Role id number: " + id + " not found in system!");
        }
    }

    @Override
    public ResponseEntity<String> updateRoleById(RoleDTO roleDTO, Long id) {
        try {
            RoleEntity existingRole = roleRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Role id number: " + id + " not found in system!"));

            // Update customer fields based on dto data
            existingRole.setRoleName(roleDTO.getRoleName());

            // Saving changes on the database
            roleRepository.save(existingRole);

            return ResponseEntity.ok("Role updated successfully");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error updating role: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return ResponseEntity.ok("Role removed successfully");
        } else {
            throw new BusinessException("Role with number ID: " + id + " not found in system!");
        }
    }
}
