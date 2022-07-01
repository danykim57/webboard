package com.webboard.configure;

import com.webboard.service.UserService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers(".login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login.html")
                .defaultSuccessUrl("/welcome.html", true)
                .failureUrl("/login.html?error=true")
                .and()
                .logout()
                .logoutUrl("/logout.html")
                .deleteCookies("JSESSIONID");
//                .logoutSuccessHandler(logoutSuccessHandler());
    }

//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth, UserService userService) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//    }
}
