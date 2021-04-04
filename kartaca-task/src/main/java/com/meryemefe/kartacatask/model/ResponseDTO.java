package com.meryemefe.kartacatask.model;

import java.sql.Timestamp;

public class ResponseDTO {

    private MethodType methodType;
    private int responseTime;
    private Timestamp timestamp;

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "methodType=" + methodType +
                ", responseTime=" + responseTime +
                ", timestamp=" + timestamp +
                '}';
    }

    public String toStringForFile() {
        return methodType.name() + " " + responseTime + " " + timestamp.getTime() + "\n";
    }
}
