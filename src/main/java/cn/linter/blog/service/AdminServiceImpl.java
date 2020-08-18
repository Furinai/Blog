package cn.linter.blog.service;

import cn.linter.blog.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Map<String, Integer> countArticleAndComment() {
        return adminMapper.countArticleAndComment();
    }
}
