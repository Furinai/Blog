package cn.linter.blog.controller;

import cn.linter.blog.entity.Comment;
import cn.linter.blog.entity.Response;
import cn.linter.blog.entity.User;
import cn.linter.blog.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAuthenticated()")
    public Response<?> addComment(@RequestBody Comment comment, @AuthenticationPrincipal User user) {
        comment.setUser(user);
        int result = commentService.addComment(comment);
        if (result == 1) {
            PageInfo<?> pageInfo = commentService.listComments(comment.getArticleId(), 1, 10);
            return Response.success("评论成功！", pageInfo);
        }
        return Response.error("评论失败！");
    }

    @PutMapping("/comment")
    @PreAuthorize("hasRole('admin')")
    public Response<?> updateComment(@RequestBody Comment comment) {
        int result = commentService.updateComment(comment);
        if (result == 1) {
            return Response.success("编辑成功！");
        }
        return Response.error("编辑失败！");
    }

    @DeleteMapping("/comment")
    @PreAuthorize("hasRole('admin')")
    public Response<?> deleteComment(@RequestBody int[] ids) {
        int result = commentService.deleteComment(ids);
        if (result > 0) {
            return Response.success("删除成功！");
        }
        return Response.error("删除失败！");
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
