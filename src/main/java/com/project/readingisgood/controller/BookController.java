package com.project.readingisgood.controller;

import com.project.readingisgood.dto.request.BookCreationRequest;
import com.project.readingisgood.dto.response.BookInfoResponse;
import com.project.readingisgood.dto.response.BookStockExpandResponse;
import com.project.readingisgood.exception.model.BookNotFound;
import com.project.readingisgood.exception.model.InternalServerError;
import com.project.readingisgood.exception.model.PriceNotFound;
import com.project.readingisgood.exception.model.QuantityBadRequest;
import com.project.readingisgood.exception.model.StockNotFound;
import com.project.readingisgood.exception.model.TokenUnauthorized;
import com.project.readingisgood.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "Persist New Book", response = BookInfoResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Persist New Book Operation Is Successfully", response = BookInfoResponse.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class),
    })
    @PostMapping("")
    public BookInfoResponse saveBook(@RequestBody BookCreationRequest bookRequest) {
        return this.bookService.saveBook(bookRequest);
    }

    @ApiOperation(value = "Update Book Stock", response = BookStockExpandResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Stock Updated Successfully", response = BookStockExpandResponse.class),
            @ApiResponse(code = 400, message = "Quantity Bad Request", response = QuantityBadRequest.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = BookNotFound.class),
            @ApiResponse(code = 404, message = "Stock Not Found", response = StockNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class),
    })
    @PutMapping("/{id}/stock/{quantity}")
    public BookStockExpandResponse addStock(@PathVariable(required = true, value = "id") Long id,
                                            @PathVariable(required = true, value = "quantity") Integer quantity) {
        return this.bookService.addStock(id, quantity);
    }

    @ApiOperation(value = "Get Book Info By Id", response = BookInfoResponse.class, authorizations = { @Authorization(value="jwtToken") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book Info Got Successfully", response = BookInfoResponse.class),
            @ApiResponse(code = 401, message = "Token Unauthorized", response = TokenUnauthorized.class),
            @ApiResponse(code = 404, message = "Book Not Found", response = BookNotFound.class),
            @ApiResponse(code = 404, message = "Stock Not Found", response = StockNotFound.class),
            @ApiResponse(code = 404, message = "Price Not Found", response = PriceNotFound.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = InternalServerError.class),
    })
    @GetMapping("/{id}")
    public BookInfoResponse getBook(@PathVariable(required = true, value = "id") Long id) {
        return this.bookService.getBookInfo(id);
    }

}
