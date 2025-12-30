package com.threadboost.dto;

public class PerformanceMetricsDTO {
    private String operationType;
    private Long executionTime;
    private Integer recordsProcessed;
    private String threadName;
    private Long timestamp;
    private Double improvementPercentage;

    public PerformanceMetricsDTO() {}

    public PerformanceMetricsDTO(String operationType, Long executionTime, Integer recordsProcessed,
                                 String threadName, Long timestamp, Double improvementPercentage) {
        this.operationType = operationType;
        this.executionTime = executionTime;
        this.recordsProcessed = recordsProcessed;
        this.threadName = threadName;
        this.timestamp = timestamp;
        this.improvementPercentage = improvementPercentage;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Integer getRecordsProcessed() {
        return recordsProcessed;
    }

    public void setRecordsProcessed(Integer recordsProcessed) {
        this.recordsProcessed = recordsProcessed;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getImprovementPercentage() {
        return improvementPercentage;
    }

    public void setImprovementPercentage(Double improvementPercentage) {
        this.improvementPercentage = improvementPercentage;
    }
}