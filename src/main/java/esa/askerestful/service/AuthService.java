package esa.askerestful.service;

import esa.askerestful.entity.User;
import esa.askerestful.model.LoginUserRequest;
import esa.askerestful.model.TokenResponse;
import esa.askerestful.repository.UserRepository;
import esa.askerestful.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private MicroService microService;

    @Transactional
    public TokenResponse login(LoginUserRequest request){
        validationService.validate(request);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED , "username atau password kamu salah"
                ));

        if(BCrypt.checkpw(request.getPassword() , user.getPassword())){
            user.setToken(microService.tokenGenerator());
            user.setTokenExpiredAt(microService.next30Days());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "username atau password anda salah");
        }
    }

    @Transactional
    public void logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }

}
