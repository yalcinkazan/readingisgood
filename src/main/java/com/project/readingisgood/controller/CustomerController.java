package com.project.readingisgood.controller;

import com.project.readingisgood.dto.request.CustomerRegisterRequest;
import com.project.readingisgood.dto.response.CustomerRegisterResponse;
import com.project.readingisgood.dto.response.OrderResponse;
import com.project.readingisgood.exception.model.CustomerNotFound;
import com.project.readingisgood.exception.model.EmailConflict;
import com.project.readingisgood.exception.model.EmailNotValid;
import com.project.readingisgood.exception.model.InternalServerError;
import com.project.readingisgood.exception.model.OrderNotFound;
import com.project.readingisgood.exception.model.TokenUnauthorized;
import com.project.readingisgood.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "Persist New Customer", response = CustomerRegisterResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persist New Customer Operation Is Successfully", response = CustomerRegisterResponse.class),
            @ApiResponse(code = 400, message = "Email Not Valid", response = EmailNotValid.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 409, message = "Email Is Already In Use", response = EmailConflict.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class),
    })
    @PostMapping("")
    public CustomerRegisterResponse addCustomer(@RequestBody CustomerRegisterRequest customerRegisterRequest) {
        return this.customerService.saveCustomer(customerRegisterRequest);
    }

    @ApiOperation(value = "Get All Orders By Customer Id", response = OrderResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All Customer Orders Got Successfully", response = OrderResponse.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 404, message = "Customer Not Found", response = CustomerNotFound.class),
            @ApiResponse(code = 404, message = "Order Not Found", response = OrderNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class),
    })
    @GetMapping("/{id}/orders/{pageNo}/{pageSize}")
    public List<OrderResponse> getAllOrders(@PathVariable(required = true, value = "id") Long id,
                                            @PathVariable(value = "pageNo") int pageNo,
                                            @PathVariable(value = "pageSize") int pageSize) {
        return this.customerService.getAllOrdersByCustomer(id, pageNo, pageSize);
    }

}
