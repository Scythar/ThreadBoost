package com.threadboost.controller;

import com.threadboost.dto.PerformanceMetricsDTO;
import com.threadboost.dto.ProductDTO;
import com.threadboost.dto.UserDTO;
import com.threadboost.service.BlockingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocking")
public class BlockingController {

    private static final Logger log = LoggerFactory.getLogger(BlockingController.class);
    private final BlockingService blockingService;

    public BlockingController(BlockingService blockingService) {
        this.blockingService = blockingService;
    }

    @GetMapping("/users")
    public ResponseEntity<PerformanceMetricsDTO> getAllUsers() {
        long startTime = System.currentTimeMillis();

        List<UserDTO> users = blockingService.getAllUsersBlocking();

        long executionTime = System.currentTimeMillis() - startTime;
        PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                "READ_USERS_BLOCKING",
                executionTime,
                users.size(),
                Thread.currentThread().getName(),
                System.currentTimeMillis(),
                0.0
        );

        log.info("Blocking read completed in {} ms", executionTime);
        return ResponseEntity.ok(metrics);
    }

    @PostMapping("/users")
    public ResponseEntity<PerformanceMetricsDTO> createUser(@RequestBody UserDTO userDTO) {
        long startTime = System.currentTimeMillis();

        UserDTO createdUser = blockingService.createUserBlocking(userDTO);

        long executionTime = System.currentTimeMillis() - startTime;
        PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                "CREATE_USER_BLOCKING",
                executionTime,
                1,
                Thread.currentThread().getName(),
                System.currentTimeMillis(),
                0.0
        );

        log.info("Blocking create completed in {} ms", executionTime);
        return ResponseEntity.ok(metrics);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<PerformanceMetricsDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        long startTime = System.currentTimeMillis();

        UserDTO updatedUser = blockingService.updateUserBlocking(id, userDTO);

        long executionTime = System.currentTimeMillis() - startTime;
        PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                "UPDATE_USER_BLOCKING",
                executionTime,
                1,
                Thread.currentThread().getName(),
                System.currentTimeMillis(),
                0.0
        );

        log.info("Blocking update completed in {} ms", executionTime);
        return ResponseEntity.ok(metrics);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<PerformanceMetricsDTO> deleteUser(@PathVariable Long id) {
        long startTime = System.currentTimeMillis();

        blockingService.deleteUserBlocking(id);

        long executionTime = System.currentTimeMillis() - startTime;
        PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                "DELETE_USER_BLOCKING",
                executionTime,
                1,
                Thread.currentThread().getName(),
                System.currentTimeMillis(),
                0.0
        );

        log.info("Blocking delete completed in {} ms", executionTime);
        return ResponseEntity.ok(metrics);
    }

    @GetMapping("/products")
    public ResponseEntity<PerformanceMetricsDTO> getAllProducts() {
        long startTime = System.currentTimeMillis();

        List<ProductDTO> products = blockingService.getAllProductsBlocking();

        long executionTime = System.currentTimeMillis() - startTime;
        PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                "READ_PRODUCTS_BLOCKING",
                executionTime,
                products.size(),
                Thread.currentThread().getName(),
                System.currentTimeMillis(),
                0.0
        );

        log.info("Blocking read products completed in {} ms", executionTime);
        return ResponseEntity.ok(metrics);
    }

    @PostMapping("/products")
    public ResponseEntity<PerformanceMetricsDTO> createProduct(@RequestBody ProductDTO productDTO) {
        long startTime = System.currentTimeMillis();

        ProductDTO createdProduct = blockingService.createProductBlocking(productDTO);

        long executionTime = System.currentTimeMillis() - startTime;
        PerformanceMetricsDTO metrics = new PerformanceMetricsDTO(
                "CREATE_PRODUCT_BLOCKING",
                executionTime,
                1,
                Thread.currentThread().getName(),
                System.currentTimeMillis(),
                0.0
        );

        log.info("Blocking create product completed in {} ms", executionTime);
        return ResponseEntity.ok(metrics);
    }
}