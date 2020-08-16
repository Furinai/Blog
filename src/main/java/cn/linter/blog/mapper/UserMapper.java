package cn.linter.blog.mapper;

import cn.linter.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int insertUser(User user);

    int updateUser(User user);

    int deleteUserByIds(@Param("ids") int[] ids);

    User selectUserById(@Param("Id") int id);

    User selectUserByEmail(@Param("email") String email);

    User selectUserByUsername(@Param("username") String username);

    List<User> selectUsers();
}