package com.bank.core.user.service;

import org.springframework.stereotype.Service;
import com.bank.core.user.dto.CreateUserRequest;
import com.bank.core.user.entity.User;
import com.bank.core.user.repository.UserRepository;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }

    public User createUser(CreateUserRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        return repo.save(user);
    }

       public List<User> getUsers(){
        return repo.findAll();
    }
}
