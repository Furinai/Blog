package cn.linter.blog.entity;

import lombok.Data;

@Data
public class Response<T> {

    private String status;
    private String message;
    private Long total;
    private T data;

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(String status, String message, Long total, T data) {
        this.status = status;
        this.message = message;
        this.total = total;
        this.data = data;
    }

    public static <T> Response<T> success(String message) {
        return new Response<T>("success", message);
    }

    public static <T> Response<T> error(String message) {
        return new Response<T>("error", message);
    }
    public static <T> Response<T> success(String message, T data) {
        return new Response<T>("success", message, data);
    }

    public static <T> Response<T> success(String message, Long total, T data) {
        return new Response<T>("success", message, total, data);
    }

}
