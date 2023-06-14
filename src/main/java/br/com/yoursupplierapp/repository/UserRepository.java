package br.com.yoursupplierapp.repository;

import br.com.yoursupplierapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserNameByEmail(String email);
    Optional<UserEntity> findByUserName(String userName);
}
