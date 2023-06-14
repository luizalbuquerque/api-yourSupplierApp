package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.dto.GroupDto;
import br.com.yoursupplierapp.entity.GroupEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.GroupRepository;
import br.com.yoursupplierapp.service.GroupService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void createGroup(@NotBlank(message = "O nome da role não pode estar em branco") GroupDto groupDto) {

        isExistentGroup(groupRepository, groupDto);

        try {
            GroupEntity groupEntity = new GroupEntity();
            if (StringUtils.hasText(groupDto.getGroupName())) {
                groupEntity.setGroupName(groupDto.getGroupName());
                groupRepository.save(groupEntity);
            } else {
                throw new IllegalArgumentException("O nome da role não pode ser nulo ou em branco");
            }

        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Erro ao criar usuario: " + e.getMessage());
        }
    }

    @Override
    public void isExistentGroup(GroupRepository groupRepository, GroupDto groupDto) throws BusinessException {
        if (groupRepository.findGroupByGroupName(groupDto.getGroupName()).isPresent()) {
            throw new BusinessException("Role name: " + groupDto.getGroupName() + " already registered in the system!");
        }
    }
}
