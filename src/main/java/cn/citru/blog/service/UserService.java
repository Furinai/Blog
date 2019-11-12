package cn.citru.blog.service;

import cn.citru.blog.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    int register(User user);

    User getUserById(int Id);
}
