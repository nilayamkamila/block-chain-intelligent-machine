package com.blockchains.stream.processor;

import java.io.Serializable;

public class LambdaResponse implements Serializable {
    private static final long serialVersionUID = 8145257839787754633L;
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventSource() {
        return eventSource;
    }

    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    public String getEventSourceARN() {
        return eventSourceARN;
    }

    public void setEventSourceARN(String eventSourceARN) {
        this.eventSourceARN = eventSourceARN;
    }

    public String getInvokeIdentityArn() {
        return invokeIdentityArn;
    }

    public void setInvokeIdentityArn(String invokeIdentityArn) {
        this.invokeIdentityArn = invokeIdentityArn;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    public String getApproximateArrivalTimestamp() {
        return approximateArrivalTimestamp;
    }

    public void setApproximateArrivalTimestamp(String approximateArrivalTimestamp) {
        this.approximateArrivalTimestamp = approximateArrivalTimestamp;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LambdaResponse() {}
    private String eventId;
    private String eventSource;
    private String eventSourceARN;
    private String invokeIdentityArn;
    private String sequenceNumber;
    private String partitionKey;
    private String approximateArrivalTimestamp;
}
