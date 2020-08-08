package cn.linter.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Data
@Alias("User")
public class User implements UserDetails {
    private Integer id;
    private String email;
    private String username;
    private String password;
    private List<Role> roles;
    private static final long serialVersionUID = 1L;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public List<Role> getAuthorities() {
        return roles;
    }
}