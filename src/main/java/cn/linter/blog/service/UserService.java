package cn.linter.blog.service;

import cn.linter.blog.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    int register(User user);

    User getUserById(int id);
}
