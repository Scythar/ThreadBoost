package com.threadboost.service;

import com.threadboost.dto.ProductDTO;
import com.threadboost.dto.UserDTO;
import com.threadboost.exception.ResourceNotFoundException;
import com.threadboost.model.Product;
import com.threadboost.model.User;
import com.threadboost.repository.ProductRepository;
import com.threadboost.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class NonBlockingService {

    private static final Logger log = LoggerFactory.getLogger(NonBlockingService.class);

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public NonBlockingService(UserRepository userRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Async("taskExecutor")
    @Transactional(readOnly = true)
    public CompletableFuture<List<UserDTO>> getAllUsersNonBlocking() {
        log.info("Non-Blocking: Fetching all users - Thread: {}", Thread.currentThread().getName());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return CompletableFuture.completedFuture(userDTOs);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<UserDTO> createUserNonBlocking(UserDTO userDTO) {
        log.info("Non-Blocking: Creating user - Thread: {}", Thread.currentThread().getName());

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        User user = modelMapper.map(userDTO, User.class);
        User savedUser = userRepository.save(user);
        UserDTO result = modelMapper.map(savedUser, UserDTO.class);

        return CompletableFuture.completedFuture(result);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<UserDTO> updateUserNonBlocking(Long id, UserDTO userDTO) {
        log.info("Non-Blocking: Updating user {} - Thread: {}", id, Thread.currentThread().getName());

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());

        User updatedUser = userRepository.save(user);
        UserDTO result = modelMapper.map(updatedUser, UserDTO.class);

        return CompletableFuture.completedFuture(result);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<Void> deleteUserNonBlocking(Long id) {
        log.info("Non-Blocking: Deleting user {} - Thread: {}", id, Thread.currentThread().getName());

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);

        return CompletableFuture.completedFuture(null);
    }

    @Async("taskExecutor")
    @Transactional(readOnly = true)
    public CompletableFuture<List<ProductDTO>> getAllProductsNonBlocking() {
        log.info("Non-Blocking: Fetching all products - Thread: {}", Thread.currentThread().getName());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());

        return CompletableFuture.completedFuture(productDTOs);
    }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<ProductDTO> createProductNonBlocking(ProductDTO productDTO) {
        log.info("Non-Blocking: Creating product - Thread: {}", Thread.currentThread().getName());

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepository.save(product);
        ProductDTO result = modelMapper.map(savedProduct, ProductDTO.class);

        return CompletableFuture.completedFuture(result);
    }
}