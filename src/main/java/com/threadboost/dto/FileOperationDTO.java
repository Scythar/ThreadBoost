package com.threadboost.dto;

public class FileOperationDTO {
    private String fileName;
    private String content;
    private Long fileSize;
    private String operation;
    private Long executionTime;

    public FileOperationDTO() {}

    public FileOperationDTO(String fileName, String content, Long fileSize,
                            String operation, Long executionTime) {
        this.fileName = fileName;
        this.content = content;
        this.fileSize = fileSize;
        this.operation = operation;
        this.executionTime = executionTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }
}