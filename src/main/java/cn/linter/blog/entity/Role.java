package cn.linter.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("Role")
public class Role implements Serializable {
    private int id;
    private String name;
    private static final long serialVersionUID = 1L;
}
