package cn.linter.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Alias("Comment")
public class Comment implements Serializable {
    private Integer id;
    private User user;
    private String content;
    private Integer articleId;
    private LocalDateTime createdTime;
    private static final long serialVersionUID = 1L;
}
