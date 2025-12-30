package com.threadboost.controller;

import com.threadboost.dto.PerformanceMetricsDTO;
import com.threadboost.dto.ProductDTO;
import com.threadboost.dto.UserDTO;
import com.threadboost.service.NonBlockingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/non-blocking")
public class NonBlockingController {

    private static final Logger log = LoggerFactory.getLogger(NonBlockingController.class);
    private final NonBlockingService nonBlockingService;

    public NonBlockingController(NonBlockingService nonBlockingService) {
        this.nonBlockingService = nonBlockingService;
    }

    @GetMapping("/users")
    public CompletableFuture<ResponseEntity<PerformanceMetricsDTO>> getAllUsers() {
        long startTime = System.currentTimeMillis();

        return nonBlockingService.getAllUsersNonBlocking()
                .thenApply(users -> {
                    long executionTime = System.currentTimeMillis() - startTime;
                    PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                            "READ_USERS_NON_BLOCKING",
                            executionTime,
                            users.size(),
                            Thread.currentThread().getName(),
                            System.currentTimeMillis(),
                            0.0
                    );

                    log.info("Non-blocking read completed in {} ms", executionTime);
                    return ResponseEntity.ok(metrics);
                });
    }

    @PostMapping("/users")
    public CompletableFuture<ResponseEntity<PerformanceMetricsDTO>> createUser(@RequestBody UserDTO userDTO) {
        long startTime = System.currentTimeMillis();

        return nonBlockingService.createUserNonBlocking(userDTO)
                .thenApply(createdUser -> {
                    long executionTime = System.currentTimeMillis() - startTime;
                    PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                            "CREATE_USER_NON_BLOCKING",
                            executionTime,
                            1,
                            Thread.currentThread().getName(),
                            System.currentTimeMillis(),
                            0.0
                    );

                    log.info("Non-blocking create completed in {} ms", executionTime);
                    return ResponseEntity.ok(metrics);
                });
    }

    @PutMapping("/users/{id}")
    public CompletableFuture<ResponseEntity<PerformanceMetricsDTO>> updateUser(
            @PathVariable Long id, @RequestBody UserDTO userDTO) {
        long startTime = System.currentTimeMillis();

        return nonBlockingService.updateUserNonBlocking(id, userDTO)
                .thenApply(updatedUser -> {
                    long executionTime = System.currentTimeMillis() - startTime;
                    PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                            "UPDATE_USER_NON_BLOCKING",
                            executionTime,
                            1,
                            Thread.currentThread().getName(),
                            System.currentTimeMillis(),
                            0.0
                    );

                    log.info("Non-blocking update completed in {} ms", executionTime);
                    return ResponseEntity.ok(metrics);
                });
    }

    @DeleteMapping("/users/{id}")
    public CompletableFuture<ResponseEntity<PerformanceMetricsDTO>> deleteUser(@PathVariable Long id) {
        long startTime = System.currentTimeMillis();

        return nonBlockingService.deleteUserNonBlocking(id)
                .thenApply(result -> {
                    long executionTime = System.currentTimeMillis() - startTime;
                    PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                            "DELETE_USER_NON_BLOCKING",
                            executionTime,
                            1,
                            Thread.currentThread().getName(),
                            System.currentTimeMillis(),
                            0.0
                    );

                    log.info("Non-blocking delete completed in {} ms", executionTime);
                    return ResponseEntity.ok(metrics);
                });
    }

    @GetMapping("/products")
    public CompletableFuture<ResponseEntity<PerformanceMetricsDTO>> getAllProducts() {
        long startTime = System.currentTimeMillis();

        return nonBlockingService.getAllProductsNonBlocking()
                .thenApply(products -> {
                    long executionTime = System.currentTimeMillis() - startTime;
                    PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                            "READ_PRODUCTS_NON_BLOCKING",
                            executionTime,
                            products.size(),
                            Thread.currentThread().getName(),
                            System.currentTimeMillis(),
                            0.0
                    );

                    log.info("Non-blocking read products completed in {} ms", executionTime);
                    return ResponseEntity.ok(metrics);
                });
    }

    @PostMapping("/products")
    public CompletableFuture<ResponseEntity<PerformanceMetricsDTO>> createProduct(@RequestBody ProductDTO productDTO) {
        long startTime = System.currentTimeMillis();

        return nonBlockingService.createProductNonBlocking(productDTO)
                .thenApply(createdProduct -> {
                    long executionTime = System.currentTimeMillis() - startTime;
                    PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                            "CREATE_PRODUCT_NON_BLOCKING",
                            executionTime,
                            1,
                            Thread.currentThread().getName(),
                            System.currentTimeMillis(),
                            0.0
                    );

                    log.info("Non-blocking create product completed in {} ms", executionTime);
                    return ResponseEntity.ok(metrics);
                });
    }
}