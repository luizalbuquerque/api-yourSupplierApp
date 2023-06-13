package br.com.yoursupplierapp.service;

import br.com.yoursupplierapp.dto.UserDTO;
import br.com.yoursupplierapp.entity.UserEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<UserEntity> findUserById(Long idUser);

    void createUser(UserDTO userDTO);

    void isExistentUser(UserRepository userRepository, UserDTO userDTO) throws BusinessException;

    ResponseEntity<String> updateUserById(UserDTO userDTO, Long id);
}
