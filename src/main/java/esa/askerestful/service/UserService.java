package esa.askerestful.service;

import esa.askerestful.entity.User;
import esa.askerestful.model.RegisterUserRequest;
import esa.askerestful.model.UpdateUserRequest;
import esa.askerestful.model.UserResponse;
import esa.askerestful.repository.UserRepository;
import esa.askerestful.security.BCrypt;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private MicroService microService;
    public void register(RegisterUserRequest request){
        validationService.validate(request);

        if(userRepository.existsByUsername(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "username sudah dipakai");
        }
        if(userRepository.existByEmail(request.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "email sudah dipakai");
        }

        User user = new User();
        user.setIdUser(microService.idUserGenerator());
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword() , BCrypt.gensalt()));
        user.setEmail(request.getEmail());
        userRepository.save(user);
    }

    public UserResponse get(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public UserResponse update(UpdateUserRequest request , User user){
        validationService.validate(request);

        if(Objects.nonNull(request.getUsername())){
            user.setUsername(request.getUsername());
        }
        if(Objects.nonNull(request.getEmail())){
            user.setEmail(request.getEmail());
        }
        if(Objects.nonNull(request.getPassword())){
            user.setPassword(BCrypt.hashpw(request.getPassword() , BCrypt.gensalt()));
        }

        userRepository.save(user);

        return UserResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }


}
