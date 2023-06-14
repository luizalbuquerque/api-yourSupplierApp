package br.com.yoursupplierapp.controller;


import br.com.yoursupplierapp.dto.RoleDTO;
import br.com.yoursupplierapp.dto.UserDTO;
import br.com.yoursupplierapp.entity.RoleEntity;
import br.com.yoursupplierapp.entity.UserEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.RoleRepository;
import br.com.yoursupplierapp.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    private final RoleRepository roleRepository;

    public RoleController(RoleService roleService, RoleRepository roleRepository) {
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody RoleDTO roleDto) {
        try {
            roleService.createRole(roleDto);
            return ResponseEntity.ok("Role created successfully");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error to create role: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<RoleEntity>> list() {
        List<RoleEntity> user = roleRepository.findAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return roleService.findRoleById(id);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error to found role: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(
            @RequestBody RoleDTO roleDTO,
            @PathVariable("id") Long id) {
        return roleService.updateRoleById(roleDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id) {
        try {
            return roleService.deleteById(id);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Erro ao deletar role: " + e.getMessage());
        }
    }


}
