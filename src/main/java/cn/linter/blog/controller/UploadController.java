package cn.linter.blog.controller;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/api")
public class UploadController {
    @PostMapping("/image")
    public Map<String, String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> map = new HashMap<>();
        String fileName = file.getOriginalFilename();
        String filePath = new ApplicationHome(getClass()).getSource().getParent();
        String path = filePath + "/static/image/" + fileName;
        Files.write(Paths.get(path), file.getBytes());
        map.put("location", "/image/" + fileName);
        return map;
    }
}
