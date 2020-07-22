package cn.linter.blog.controller;

import cn.linter.blog.entity.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("api/admin")
public class UploadController {

    @Value("${blog.upload.image.location}")
    private String location;

    @PostMapping("/image")
    public Response<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = LocalDate.now().format(formatter);
        String filePath = location + "/" + date + "/";
        File folder = new File(filePath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                return Response.error("文件夹创建失败！");
            }
        }
        String fileName = file.getOriginalFilename();
        Files.write(Paths.get(filePath + fileName), file.getBytes());
        return Response.success("上传成功！", "/upload/image/" + date + "/" + fileName);
    }
}
