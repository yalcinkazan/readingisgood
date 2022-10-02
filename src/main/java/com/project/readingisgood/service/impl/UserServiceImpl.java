package com.project.readingisgood.service.impl;

import com.project.readingisgood.entity.Users;
import com.project.readingisgood.repository.UserRepository;
import com.project.readingisgood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Users> findUserByUsernameAndPassword(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

}
