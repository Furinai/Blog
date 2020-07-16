package cn.linter.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Category")
public class Category {
    private int id;
    private String name;
    private String icon;
    private int sequence;
}
