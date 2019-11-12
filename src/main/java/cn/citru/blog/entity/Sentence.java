package cn.citru.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("Sentence")
public class Sentence {
    private String note;
    private String content;
    private Timestamp createTime;
}
