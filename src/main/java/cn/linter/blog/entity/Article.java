package cn.linter.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Alias("Article")
public class Article implements Serializable {
    private int id;
    private String title;
    private String synopsis;
    private String content;
    private int viewCount;
    private int commentCount;
    private Category category;
    private LocalDateTime createTime;
    private static final long serialVersionUID = 1L;
}
