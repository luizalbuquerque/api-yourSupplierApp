package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.dto.UserDTO;
import br.com.yoursupplierapp.entity.UserEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.UserRepository;
import br.com.yoursupplierapp.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.yoursupplierapp.utils.ConstantUtils.DUPLICATED_USER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDTO userDTO) {

        isExistentUser(userRepository, userDTO);

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(userDTO.getUserName());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setPassword(userDTO.getPassword());
            userRepository.save(userEntity);
            //If there is already a user with this userName and this Email.
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(DUPLICATED_USER);
        }
    }

    @Override
    public ResponseEntity<UserEntity> findUserById(Long id) {
        try {
            Optional<UserEntity> clientOptional = userRepository.findById(id);
            if (!clientOptional.isPresent()) {
                throw new BusinessException("Cliente com número de ID: " + id + " não foi encontrado no sistema!");
            }
            UserEntity client = clientOptional.get();
            return ResponseEntity.ok(client);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Cliente com número de ID: " + id + " não foi encontrado no sistema!");
        }
    }



    @Override
    public void isExistentUser(UserRepository userRepository, UserDTO userDTO) throws BusinessException {
        if (userRepository.findUserNameByEmail(userDTO.getEmail()).isPresent()) {
            throw new BusinessException("Usuario com email: " + userDTO.getEmail() + " já cadastrado no sistema!");
        }
        if (userRepository.findByUserName(userDTO.getUserName()).isPresent()) {
            throw new BusinessException("Usuario com nome: " + userDTO.getUserName() + " já cadastrado no sistema!");
        }
    }
}
