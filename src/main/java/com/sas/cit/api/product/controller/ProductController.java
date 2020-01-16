package com.sas.cit.api.product.controller;

import com.sas.cit.api.product.model.ProductResponse;
import com.sas.cit.api.product.model.ProductVo;
import com.sas.cit.api.product.service.ProductService;
import com.sas.cit.entity.model.Product;
import com.sas.cit.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService service;

    @GetMapping(value = "sas/v1/products", produces = "application/json")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "sas/v1/products/{id}", produces = "application/json")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long id) throws BusinessException {
        return ResponseEntity.ok(service.retrieveProductById(id));
    }

    @PostMapping(value = "sas/v1/products", consumes = "application/json")
    public ResponseEntity<ProductResponse> postProduct(@RequestBody ProductVo productVo) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createProduct(productVo));
    }
}
