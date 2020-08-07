### 简介
基于SpringBoot和Nuxt的前后端分离博客，线上地址：[citru.cn](https://citru.cn)
### 项目地址
后端项目：[https://github.com/citrucn/Blog](https://github.com/citrucn/Blog)  
后端码云地址：[https://gitee.com/citru/Blog](https://gitee.com/citru/Blog)  
前端项目：[https://github.com/citrucn/Blog-Frontend](https://github.com/citrucn/Blog-Frontend)  
前端码云项目：[https://gitee.com/citru/Blog-Frontend](https://gitee.com/citru/Blog-Frontend)
### Nginx配置
```
location / {
    proxy_pass http://127.0.0.1:3000;
}

location ~ .*\.(gif|jpg|jpeg|png)$
{
    proxy_pass http://127.0.0.1:3000;
}
    
location ~ .*\.(js|css|woff|ttf|ico)?$
{
    proxy_pass http://127.0.0.1:3000;
}
```