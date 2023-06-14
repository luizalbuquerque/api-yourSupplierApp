package br.com.yoursupplierapp.controller;

import br.com.yoursupplierapp.dto.GroupDto;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.GroupRepository;
import br.com.yoursupplierapp.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    private final GroupRepository groupRepository;

    public GroupController(GroupService groupService, GroupRepository groupRepository) {
        this.groupService = groupService;
        this.groupRepository = groupRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody GroupDto groupDto) {
        try {
            groupService.createGroup(groupDto);
            return ResponseEntity.ok("Role created successfully");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error to create role: " + e.getMessage());
        }
    }
}
