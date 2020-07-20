package cn.linter.blog.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;

@Data
@Alias("Role")
public class Role implements GrantedAuthority {
    private int id;
    private String name;
    private static final long serialVersionUID = 1L;

    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }
}
