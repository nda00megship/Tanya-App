package esa.askerestful.service;

import org.springframework.stereotype.Service;

@Service
public class MicroService {

    private int currentId = 0;
    public String idGenerator(){
        String userId = "USER_" + String.format("%04d" , currentId);
        currentId++;
        return userId;
    }

    public String tokenGenerator(){
        String userId = "TOKEN_" + String.format("%04d" , currentId);
        currentId++;
        return userId;
    }

    public Long next30Days(){
        return System.currentTimeMillis() + (1000 * 16 * 24 *30);
    }
}
