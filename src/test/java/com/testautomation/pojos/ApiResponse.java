package com.testautomation.pojos;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }

    public ApiResponse<T> setSuccess(boolean success) { this.success = success; return this; }
    public ApiResponse<T> setMessage(String message) { this.message = message; return this; }
    public ApiResponse<T> setData(T data) { this.data = data; return this; }
}
