package cn.citru.blog.mapper;

import cn.citru.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertUser(User user);

    int deleteUser(@Param("id") int id);

    User selectUserById(@Param("Id") int Id);

    User selectUserByUsername(@Param("username") String username);
}