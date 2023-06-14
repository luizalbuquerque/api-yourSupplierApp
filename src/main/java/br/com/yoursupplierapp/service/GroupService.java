package br.com.yoursupplierapp.service;

import br.com.yoursupplierapp.dto.GroupDto;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.GroupRepository;

public interface GroupService {
    void createGroup(GroupDto groupDto);

    void isExistentGroup(GroupRepository groupRepository, GroupDto groupDto) throws BusinessException;
}
