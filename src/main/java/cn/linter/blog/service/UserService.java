package cn.linter.blog.service;

import cn.linter.blog.entity.User;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int[] ids);

    User getUser(int id);

    PageInfo<?> listUsers(int pageNum, int pageSize);
}
