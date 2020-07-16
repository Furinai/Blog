package cn.linter.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@Alias("Comment")
public class Comment {
    private int id;
    private User user;
    private String content;
    private int articleId;
    private LocalDateTime createTime;
}
