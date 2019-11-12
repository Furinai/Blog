package cn.citru.blog.entity;

import lombok.Data;

@Data
public class Response {
    private String status;
    private String message;
    private Long total;
    private Object object;

    public Response() {
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public Response(String status, String message, Long total, Object object) {
        this.status = status;
        this.message = message;
        this.total = total;
        this.object = object;
    }
}
