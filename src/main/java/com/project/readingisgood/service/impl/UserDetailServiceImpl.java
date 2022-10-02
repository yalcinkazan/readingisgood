package com.project.readingisgood.service.impl;

import com.project.readingisgood.entity.Users;
import com.project.readingisgood.exception.InternalServerError;
import com.project.readingisgood.service.UserDetailService;
import com.project.readingisgood.service.UserService;
import com.project.readingisgood.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<Users> user = this.userService.findByUsername(username);
            if(!user.isPresent()) throw new UsernameNotFoundException(username);
            return new User(user.get().getUsername(), this.bCryptPasswordEncoder.encode(user.get().getPassword()), new ArrayList<>());
        }catch (Exception e){
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }

}
