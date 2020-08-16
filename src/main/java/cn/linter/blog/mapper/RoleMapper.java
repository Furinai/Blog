package cn.linter.blog.mapper;

import cn.linter.blog.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    void insertRolesToUser(@Param("userId") int userId, @Param("roles") List<Role> roles);

    void deleteRolesWithUserId(@Param("userId") int userId, @Param("roles") List<Role> roles);

    void deleteRolesByUserIds(@Param("userIds") int[] userIds);

    List<Role> selectRolesByUserId(@Param("userId") int userId);

    List<Role> selectRoles();
}
