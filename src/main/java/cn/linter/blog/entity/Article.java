package cn.linter.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Alias("Article")
@Document(indexName = "article")
public class Article implements Serializable {
    @Id
    private Integer id;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String title;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String synopsis;
    @Field
    @Transient
    private String content;
    @Field
    @Transient
    private Integer viewCount;
    @Field
    @Transient
    private Integer commentCount;
    @Field
    @Transient
    private Category category;
    @Field
    @Transient
    private LocalDateTime createdTime;
    private static final long serialVersionUID = 1L;
}
