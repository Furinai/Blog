package cn.citru.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("Article")
public class Article {
    private int id;
    private User user;
    private String title;
    private String summary;
    private String content;
    private int commentCount;
    private Category category;
    private Timestamp createTime;
    private Timestamp updateTime;
}
