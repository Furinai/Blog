package cn.linter.blog.config;

import cn.linter.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenRepository tokenRepository;

    private final UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService, DataSource dataSource) {
        TokenRepository tokenRepository = new TokenRepository();
        tokenRepository.setDataSource(dataSource);
        this.tokenRepository = tokenRepository;
        this.userService = userService;
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
                    out.write("{\"status\":\"success\",\"message\":\"登录成功!\"}");
                    out.flush();
                    out.close();
                })
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write("{\"status\":\"error\",\"message\":\"账号或密码错误!\"}");
                    out.flush();
                    out.close();
                })
                .and().exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    out.write("{\"status\":\"error\",\"message\":\"授权已过期，请重新登录。\"}");
                    out.flush();
                    out.close();
                })
                .accessDeniedHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    out.write("{\"status\":\"error\",\"message\":\"您没有权限进行此操作!\"}");
                    out.flush();
                    out.close();
                })
                .and().logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write("{\"status\":\"success\",\"message\":\"注销成功!\"}");
                    out.flush();
                    out.close();
                })
                .and().rememberMe()
                .rememberMeParameter("remember")
                .tokenRepository(tokenRepository)
                .userDetailsService(userService)
                .and().csrf()
                .disable();
    }
}