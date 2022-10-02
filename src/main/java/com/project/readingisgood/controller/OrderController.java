package com.project.readingisgood.controller;

import com.project.readingisgood.dto.request.OrderCreationRequest;
import com.project.readingisgood.dto.request.OrderDateRangeRequest;
import com.project.readingisgood.dto.response.OrderResponse;
import com.project.readingisgood.exception.model.BookNotFound;
import com.project.readingisgood.exception.model.CustomerNotFound;
import com.project.readingisgood.exception.model.InternalServerError;
import com.project.readingisgood.exception.model.OrderBadRequest;
import com.project.readingisgood.exception.model.OrderDetailBadRequest;
import com.project.readingisgood.exception.model.OrderNotFound;
import com.project.readingisgood.exception.model.QuantityBadRequest;
import com.project.readingisgood.exception.model.StockBadRequest;
import com.project.readingisgood.exception.model.TokenUnauthorized;
import com.project.readingisgood.service.OrderService;
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
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "Persist New Order", response = OrderResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persist New Order Operation Is Successfully", response = OrderResponse.class),
            @ApiResponse(code = 400, message = "Order Detail Is Empty", response = OrderDetailBadRequest.class),
            @ApiResponse(code = 400, message = "Missing Order Information", response = OrderBadRequest.class),
            @ApiResponse(code = 400, message = "Quantity Have To Be More Than Zero", response = QuantityBadRequest.class),
            @ApiResponse(code = 400, message = "Not Enough Stock", response = StockBadRequest.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 404, message = "Customer Not Found", response = CustomerNotFound.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = BookNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class)
    })
    @PostMapping("")
    public OrderResponse createOrder(@RequestBody OrderCreationRequest orderCreationRequest) {
        return this.orderService.createOrder(orderCreationRequest);
    }

    @ApiOperation(value = "Get Order Info By Id", response = OrderResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Info Got Successfully", response = OrderResponse.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 404, message = "Order Not Found", response = OrderNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class),
    })
    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable(required = true, value = "id") Long id) {
        return this.orderService.getOrderById(id);
    }

    @ApiOperation(value = "Get Order Info By Date Range", response = OrderResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Info Got Successfully", response = OrderResponse.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 404, message = "Order Not Found In Date Range", response = OrderNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class),
    })
    @GetMapping("/{pageNo}/{pageSize}")
    public List<OrderResponse> getAllOrders(@RequestBody OrderDateRangeRequest orderDateRangeRequest,
                                            @PathVariable(value = "pageNo") int pageNo,
                                            @PathVariable(value = "pageSize") int pageSize) {
        return this.orderService.findByDateRange(orderDateRangeRequest, pageNo, pageSize);
    }

}
