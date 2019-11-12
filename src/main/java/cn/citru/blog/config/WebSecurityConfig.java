package cn.citru.blog.config;

import cn.citru.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                .antMatchers("/static/**").permitAll()
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/admin/**").hasRole("admin")
                .and().formLogin()
                .loginPage("/login")
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
                    out.write("{\"status\":\"error\",\"message\":\"登录失败!\"}");
                    out.flush();
                    out.close();
                })
                .and().exceptionHandling()
                .accessDeniedHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write("{\"status\":\"error\",\"message\":\"禁止操作!\"}");
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