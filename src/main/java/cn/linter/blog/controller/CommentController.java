package cn.linter.blog.controller;

import cn.linter.blog.entity.Comment;
import cn.linter.blog.entity.Response;
import cn.linter.blog.entity.User;
import cn.linter.blog.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public Response<?> addComment(@RequestBody Comment comment, @AuthenticationPrincipal User user) {
        int result = commentService.addComment(comment, user);
        if (result == 1) {
            return Response.success("评论成功！");
        }
        return Response.error("评论失败！");
    }

    @GetMapping("/comments")
    public Response<?> getComments(@RequestParam(value = "articleId", defaultValue = "0") int articleId,
                                   @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        PageInfo<?> pageInfo = commentService.listComments(articleId, pageNum, pageSize);
        if (pageInfo.getList() == null) {
            return Response.error("评论列表获取失败！");
        }
        return Response.success("评论列表获取成功！", pageInfo);
    }
}
