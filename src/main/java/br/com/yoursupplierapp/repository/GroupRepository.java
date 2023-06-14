package br.com.yoursupplierapp.repository;

import br.com.yoursupplierapp.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findGroupByGroupName (String groupName);
}
