package com.threadboost.controller;

import com.threadboost.dto.FileOperationDTO;
import com.threadboost.service.FileOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/file")
public class FileOperationController {

    private static final Logger log = LoggerFactory.getLogger(FileOperationController.class);
    private final FileOperationService fileOperationService;

    public FileOperationController(FileOperationService fileOperationService) {
        this.fileOperationService = fileOperationService;
    }

    @PostMapping("/blocking/write")
    public ResponseEntity<FileOperationDTO> writeFileBlocking(@RequestBody FileOperationDTO request) {
        log.info("Received blocking file write request");
        FileOperationDTO result = fileOperationService.writeFileBlocking(
                request.getFileName(), request.getContent());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/non-blocking/write")
    public CompletableFuture<ResponseEntity<FileOperationDTO>> writeFileNonBlocking(
            @RequestBody FileOperationDTO request) {
        log.info("Received non-blocking file write request");
        return fileOperationService.writeFileNonBlocking(request.getFileName(), request.getContent())
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/blocking/read/{fileName}")
    public ResponseEntity<FileOperationDTO> readFileBlocking(@PathVariable String fileName) {
        log.info("Received blocking file read request for: {}", fileName);
        FileOperationDTO result = fileOperationService.readFileBlocking(fileName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/non-blocking/read/{fileName}")
    public CompletableFuture<ResponseEntity<FileOperationDTO>> readFileNonBlocking(@PathVariable String fileName) {
        log.info("Received non-blocking file read request for: {}", fileName);
        return fileOperationService.readFileNonBlocking(fileName)
                .thenApply(ResponseEntity::ok);
    }
}