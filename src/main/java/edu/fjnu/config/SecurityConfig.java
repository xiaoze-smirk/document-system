package edu.fjnu.config;

import edu.fjnu.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author xiaoze
 * @date 2018/1/16
 * Spring Security 配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String KEY = "xiaoze.com";

    /**
     * 记住我的有效时间秒(下面为一周)
     */
    private static final int REMEMBER_ME_SECONDS = 60 * 60 * 24 * 7;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    PersistentTokenRepository persistentTokenRepository;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return new AESUtil().Encrypt((String)rawPassword,"ABCDEFGHIJKLMNOP");
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(new AESUtil().Encrypt((String)rawPassword,"ABCDEFGHIJKLMNOP"));
            }
        }); // 设置密码加密方式

        return authenticationProvider;
    }

    @Bean
    public RememberMeServices rememberMeServices() {

        // 此处的 key 可以为任意非空值(null 或 "")，单必须和起前面
        // rememberMeServices(RememberMeServices rememberMeServices).key(key)的值相同
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices(KEY, userDetailsService, persistentTokenRepository);

        // 该参数不是必须的，默认值为 "remember-me", 但如果设置必须和页面复选框的 name 一致
        rememberMeServices.setParameter("remember-me");
        return rememberMeServices;
    }

    /**
     * 自定义配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**",
                "/js/**",
                "/images/**",
                "/druid/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/security/toFaceLogin",
                "/security/toRegister",
                "/security/register",
                "/security/faceContrast*",
                "/index").permitAll() // 都可以访问
                .anyRequest().authenticated()
                .and()
                .formLogin()   //基于 Form 表单登录验证
                .loginPage("/security/toLogin")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successForwardUrl("/security/enter")
                    .failureUrl("/security/loginError") // 自定义登录界面
                    .permitAll()
                .and()
                    .rememberMe()
                    .rememberMeServices(rememberMeServices())
                    .tokenValiditySeconds(REMEMBER_ME_SECONDS)
                    .key(KEY)
                .and()
                .logout()
                    .logoutSuccessUrl("/security/logOut")
                .and().exceptionHandling().accessDeniedPage("/error");  // 处理异常，拒绝访问就重定向到 403 页面
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    /**
     * 认证信息管理
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
}
