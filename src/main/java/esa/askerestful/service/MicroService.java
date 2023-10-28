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
}
