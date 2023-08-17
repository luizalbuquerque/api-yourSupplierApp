package br.com.yoursupplierapp.service.impl;

import br.com.yoursupplierapp.dto.UserDTO;
import br.com.yoursupplierapp.entity.UserEntity;
import br.com.yoursupplierapp.exception.BusinessException;
import br.com.yoursupplierapp.repository.UserRepository;
import br.com.yoursupplierapp.service.UserService;
import br.com.yoursupplierapp.utils.CardStatus;
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
            userEntity.setCardStatus(CardStatus.ACTIVE);
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setGroups(userDTO.getGroups());
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
                throw new BusinessException("User id number: " + id + " not found in system!");
            }
            UserEntity client = clientOptional.get();
            return ResponseEntity.ok(client);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("User id number: " + id + " not found in system!");
        }
    }

    public ResponseEntity<String> updateUserById(UserDTO userDTO, Long id) {
        try {
            UserEntity existingClient = userRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("User id number: " + id + " not found in system!"));

            // Update customer fields based on dto data
            existingClient.setUserName(userDTO.getUserName());
            existingClient.setEmail(userDTO.getEmail());
            existingClient.setPassword(userDTO.getPassword());
            existingClient.setCardStatus(userDTO.getCardStatus());

            // Saving changes on the database
            userRepository.save(existingClient);

            return ResponseEntity.ok("User updated successfully");
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error updating client: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("Client removed successfully");
        } else {
            throw new BusinessException("User id number: " + id + " not found in system!");
        }
    }

    @Override
    public void isExistentUser(UserRepository userRepository, UserDTO userDTO) throws BusinessException {
        if (userRepository.findUserNameByEmail(userDTO.getEmail()).isPresent()) {
            throw new BusinessException("User with email: " + userDTO.getEmail() + " already registered in the system!");
        }
        if (userRepository.findByUserName(userDTO.getUserName()).isPresent()) {
            throw new BusinessException("User with name: " + userDTO.getUserName() + " already registered in the system!");
        }
    }
}

