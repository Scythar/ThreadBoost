package com.threadboost.service;

import com.threadboost.dto.FileOperationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

@Service
public class FileOperationService {

    private static final Logger log = LoggerFactory.getLogger(FileOperationService.class);
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir") + "/threadboost/";

    public FileOperationService() {
        try {
            Files.createDirectories(Paths.get(TEMP_DIR));
        } catch (IOException e) {
            log.error("Error creating temp directory", e);
        }
    }

    public FileOperationDTO writeFileBlocking(String fileName, String content) {
        log.info("Blocking: Writing file {} - Thread: {}", fileName, Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();

        try {
            Path filePath = Paths.get(TEMP_DIR + fileName);
            Files.writeString(filePath, content);

            Thread.sleep(200);

            long executionTime = System.currentTimeMillis() - startTime;
            return new FileOperationDTO(fileName, content, (long) content.length(),
                    "WRITE_BLOCKING", executionTime);
        } catch (Exception e) {
            log.error("Error writing file", e);
            throw new RuntimeException("File write failed: " + e.getMessage());
        }
    }

    @Async("fileTaskExecutor")
    public CompletableFuture<FileOperationDTO> writeFileNonBlocking(String fileName, String content) {
        log.info("Non-Blocking: Writing file {} - Thread: {}", fileName, Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();

        try {
            Path filePath = Paths.get(TEMP_DIR + fileName);
            Files.writeString(filePath, content);

            Thread.sleep(200);

            long executionTime = System.currentTimeMillis() - startTime;
            FileOperationDTO result = new FileOperationDTO(fileName, content,
                    (long) content.length(), "WRITE_NON_BLOCKING", executionTime);

            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            log.error("Error writing file", e);
            throw new RuntimeException("File write failed: " + e.getMessage());
        }
    }

    public FileOperationDTO readFileBlocking(String fileName) {
        log.info("Blocking: Reading file {} - Thread: {}", fileName, Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();

        try {
            Path filePath = Paths.get(TEMP_DIR + fileName);
            String content = Files.readString(filePath);

            Thread.sleep(150);

            long executionTime = System.currentTimeMillis() - startTime;
            return new FileOperationDTO(fileName, content, (long) content.length(),
                    "READ_BLOCKING", executionTime);
        } catch (Exception e) {
            log.error("Error reading file", e);
            throw new RuntimeException("File read failed: " + e.getMessage());
        }
    }

    @Async("fileTaskExecutor")
    public CompletableFuture<FileOperationDTO> readFileNonBlocking(String fileName) {
        log.info("Non-Blocking: Reading file {} - Thread: {}", fileName, Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();

        try {
            Path filePath = Paths.get(TEMP_DIR + fileName);
            String content = Files.readString(filePath);

            Thread.sleep(150);

            long executionTime = System.currentTimeMillis() - startTime;
            FileOperationDTO result = new FileOperationDTO(fileName, content,
                    (long) content.length(), "READ_NON_BLOCKING", executionTime);

            return CompletableFuture.completedFuture(result);
        } catch (Exception e) {
            log.error("Error reading file", e);
            throw new RuntimeException("File read failed: " + e.getMessage());
        }
    }
}