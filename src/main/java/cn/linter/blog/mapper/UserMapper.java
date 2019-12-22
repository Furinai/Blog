package cn.linter.blog.mapper;

import cn.linter.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertUser(User user);

    int deleteUser(@Param("id") int id);

    User selectUserById(@Param("Id") int Id);

    User selectUserByEmail(@Param("email") String email);

    User selectUserByUsername(@Param("username") String username);
}