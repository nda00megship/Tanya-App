package esa.askerestful.data;

import esa.askerestful.entity.User;
import esa.askerestful.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserSeeder {

    @Autowired
    private UserRepository userRepository;


    public void seedDataUser(){

        for (int i = 0 ; i < 15 ; i++){
            User user = new User();
            user.setIdUser("user_" + i);
            user.setUsername("esa"+ i);
            user.setEmail("esa" + i + "@gmail.com");
            user.setPassword("123");
            user.setToken("token"+i);
            user.setTokenExpiredAt(100000000000000L);
            userRepository.save(user);
        }
    }
}
