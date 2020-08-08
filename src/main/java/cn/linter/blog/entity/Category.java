package cn.linter.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("Category")
public class Category implements Serializable {
    private Integer id;
    private String name;
    private String icon;
    private Integer sequence;
    private static final long serialVersionUID = 1L;
}
