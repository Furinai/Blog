package cn.linter.blog.service;

import cn.linter.blog.entity.Role;
import cn.linter.blog.entity.User;
import cn.linter.blog.mapper.ArticleMapper;
import cn.linter.blog.mapper.CommentMapper;
import cn.linter.blog.mapper.RoleMapper;
import cn.linter.blog.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final ArticleMapper articleMapper;

    private final CommentMapper commentMapper;

    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, ArticleMapper articleMapper,
                           CommentMapper commentMapper, BCryptPasswordEncoder encoder) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.articleMapper = articleMapper;
        this.commentMapper = commentMapper;
        this.encoder = encoder;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("找不到用户名为: " + username + "的用户");
        }
        List<Role> roles = roleMapper.selectRolesByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        if (userMapper.selectUserByEmail(user.getEmail()) != null) {
            return -1;
        }
        if (userMapper.selectUserByUsername(user.getUsername()) != null) {
            return -2;
        }
        user.setPassword(encoder.encode(user.getPassword()));
        int result = userMapper.insertUser(user);
        if (user.getRoles() == null) {
            List<Role> roles = List.of(new Role(2, "member"));
            roleMapper.insertRolesToUser(user.getId(), roles);
        } else {
            roleMapper.insertRolesToUser(user.getId(), user.getRoles());
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        User user1 = userMapper.selectUserByEmail(user.getEmail());
        if (user1 != null && !user1.getId().equals(user.getId())) {
            return -1;
        }
        User user2 = userMapper.selectUserByUsername(user.getUsername());
        if (user2 != null && !user2.getId().equals(user.getId())) {
            return -2;
        }
        List<Role> newRoles = user.getRoles();
        List<Role> oldRoles = roleMapper.selectRolesByUserId(user.getId());
        if (!newRoles.equals(oldRoles)) {
            List<Role> common = new ArrayList<>(newRoles);
            common.retainAll(oldRoles);
            if (newRoles.equals(common)) {
                oldRoles.removeAll(common);
                roleMapper.deleteRolesWithUserId(user.getId(), oldRoles);
            } else if (oldRoles.equals(common)) {
                newRoles.removeAll(common);
                roleMapper.insertRolesToUser(user.getId(), newRoles);
            } else {
                oldRoles.removeAll(common);
                roleMapper.deleteRolesWithUserId(user.getId(), oldRoles);
                newRoles.removeAll(common);
                roleMapper.insertRolesToUser(user.getId(), newRoles);
            }
        }
        if (user.getPassword() != null) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        return userMapper.updateUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(int[] ids) {
        List<Map<String, Integer>> maps;
        maps = commentMapper.countCommentByUserIds(ids);
        if (maps.size() > 0) {
            articleMapper.decreaseCommentCount(maps);
            commentMapper.deleteCommentByUserIds(ids);
        }
        roleMapper.deleteRolesByUserIds(ids);
        return userMapper.deleteUserByIds(ids);
    }

    @Override
    public User getUser(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public PageInfo<?> listUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(userMapper.selectUsers(), 10);
    }
}