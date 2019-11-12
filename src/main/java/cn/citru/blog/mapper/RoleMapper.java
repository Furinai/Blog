package cn.citru.blog.mapper;

import cn.citru.blog.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    int insertRoleToUser(@Param("userId") int userId, @Param("roleId") int roleId);

    int deleteRoleFromUser(@Param("userId") int userId, @Param("roleId") int roleId);

    List<Role> selectRolesByUserId(@Param("userId") int userId);
}
