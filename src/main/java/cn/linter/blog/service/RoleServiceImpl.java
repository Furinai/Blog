package cn.linter.blog.service;

import cn.linter.blog.entity.Role;
import cn.linter.blog.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> listRoles() {
        return roleMapper.selectRoles();
    }
}
