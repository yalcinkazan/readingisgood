package com.project.readingisgood.controller;

import com.project.readingisgood.dto.request.LoginRequest;
import com.project.readingisgood.dto.response.LoginResponse;
import com.project.readingisgood.exception.model.InternalServerError;
import com.project.readingisgood.exception.model.UserNotFound;
import com.project.readingisgood.exception.model.UserPasswordBadRequest;
import com.project.readingisgood.service.LoginService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "Login User", response = LoginResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login and Authentication Successfully", response = LoginResponse.class),
            @ApiResponse(code = 400, message = "Wrong Password", response = UserPasswordBadRequest.class),
            @ApiResponse(code = 404, message = "User Not Found", response = UserNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class)
    })
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return this.loginService.login(loginRequest);
    }



}
