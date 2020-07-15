# Blog
基于Springboot和Vue的前后端分离博客系统

### Nginx配置
```
location / {  
    try_files $uri $uri/ /index.html;
}

location /api {
    proxy_pass http://127.0.0.1:8080;
}
```
        
### 前端项目
https://github.com/citrucn/Blog-Frontend