package waa.labs.waalabs.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    int statusCode;
    String message;
    T data;
    public ResponseDto(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ResponseDto(String message, int statusCode, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
