package com.project.readingisgood.service;

import com.project.readingisgood.dto.request.LoginRequest;
import com.project.readingisgood.dto.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    LoginResponse login(LoginRequest loginRequest);

}
