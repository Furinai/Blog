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
        String rootPath;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            rootPath = new ApplicationHome(getClass()).getSource().getPath();
        } else {
            rootPath = new ApplicationHome(getClass()).getSource().getParent();
        }
        Map<String, String> map = new HashMap<>();
        String fileName = file.getOriginalFilename();
        String filePath = rootPath + "/static/file/" + fileName;
        Files.write(Paths.get(filePath), file.getBytes());
        map.put("location", "/image/" + fileName);
        return map;
    }
}
