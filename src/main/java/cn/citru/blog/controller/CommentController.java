package cn.citru.blog.controller;

import cn.citru.blog.entity.Comment;
import cn.citru.blog.entity.Response;
import cn.citru.blog.entity.User;
import cn.citru.blog.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public Response addComment(@RequestBody Comment comment, @AuthenticationPrincipal User user) {
        if (user == null) return new Response("error", "请先登录");
        int result = commentService.addComment(comment, user);
        if (result == 0) return new Response("error", "评论失败！");
        return new Response("success", "评论成功！");
    }


    @GetMapping("/comments")
    public Response getComments(@RequestParam(value = "articleId", defaultValue = "0") int articleId,
                                @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        PageInfo pageInfo = commentService.getComments(articleId, pageNumber, pageSize);
        if (pageInfo.getList() == null) return new Response("error", "获取失败");
        return new Response("success", "获取成功", pageInfo.getTotal(), pageInfo.getList());
    }
}
