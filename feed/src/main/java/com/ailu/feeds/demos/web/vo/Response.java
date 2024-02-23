package com.ailu.feeds.demos.web.vo;


import lombok.Data;

@Data
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Response(int code) {
        this.code = code;
    }

    public Response() {
    }
}
