package com.project.readingisgood.service.impl;

import com.project.readingisgood.component.TokenManager;
import com.project.readingisgood.dto.request.LoginRequest;
import com.project.readingisgood.dto.response.LoginResponse;
import com.project.readingisgood.entity.Users;
import com.project.readingisgood.enums.AuthorizationStatus;
import com.project.readingisgood.enums.TokenType;
import com.project.readingisgood.exception.InternalServerError;
import com.project.readingisgood.exception.UserNotFoundException;
import com.project.readingisgood.exception.UserPasswordBadRequestException;
import com.project.readingisgood.service.LoginService;
import com.project.readingisgood.service.UserService;
import com.project.readingisgood.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;
    
    @Autowired
    private UserService userService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Optional<Users> user =  this.userService.findByUsername(loginRequest.getUsername());
            if(!user.isPresent()) throw new UserNotFoundException(loginRequest.getUsername());
            if(!user.get().getPassword().equals(loginRequest.getPassword())) throw new UserPasswordBadRequestException();
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            LOGGER.info("Login Successful For -> {}", loginRequest.getUsername());
            return LoginResponse.builder()
                    .message("Token Created For -> " + loginRequest.getUsername())
                    .token(this.tokenManager.generateToken(loginRequest.getUsername()))
                    .responseStatus(AuthorizationStatus.AUTHORIZED)
                    .tokenType(TokenType.BEARER)
                    .time(DateUtil.getNow()).build();
        } catch (UserNotFoundException userNotFoundException) {
            LOGGER.error("User not found with username {}", loginRequest.getUsername());
            throw new UserNotFoundException(loginRequest.getUsername());
        } catch (UserPasswordBadRequestException userPasswordBadRequestException) {
            LOGGER.error("Wrong password");
            throw new UserPasswordBadRequestException();
        } catch (Exception e) {
            throw new InternalServerError(DateUtil.getNow(), e);
        }
    }


}
