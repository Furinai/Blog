package cn.linter.blog.config;

import cn.linter.blog.entity.Response;
import cn.linter.blog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public WebSecurityConfig(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("get", "/api/auth").authenticated()
                .antMatchers("post", "/api/comment").authenticated()
                .and().formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Response responseBody = new Response("success", "登录成功！");
                    out.write(objectMapper.writeValueAsString(responseBody));
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Response responseBody = new Response("error", "账号或密码错误！");
                    out.write(objectMapper.writeValueAsString(responseBody));
                    out.flush();
                    out.close();
                })
                .and().exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    Response responseBody = new Response("error", "授权已过期，请重新登录！");
                    out.write(objectMapper.writeValueAsString(responseBody));
                    out.flush();
                    out.close();
                })
                .accessDeniedHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    Response responseBody = new Response("error", "您没有权限进行此操作！");
                    out.write(objectMapper.writeValueAsString(responseBody));
                    out.flush();
                    out.close();
                })
                .and().logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    Response responseBody = new Response("success", "注销成功！");
                    out.write(objectMapper.writeValueAsString(responseBody));
                    out.flush();
                    out.close();
                })
                .and().rememberMe()
                .rememberMeParameter("remember")
                .userDetailsService(userService)
                .and().cors(Customizer.withDefaults())
                .csrf().disable();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("content-type", "authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;


    }
}