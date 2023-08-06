package com.springBoot.SpringBootPractice.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.springBoot.SpringBootPractice.config.UserRole.ADMIN;
import static com.springBoot.SpringBootPractice.config.UserRole.EMPLOYEE;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
private final PasswordEncoder passwordEncoder;
@Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
@Override
    protected void  configure(@NotNull HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*")
                .permitAll()
                .antMatchers("/employee/**")/*.hasRole(EMPLOYEE.name())*/.permitAll()
                .antMatchers("/department/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
            UserDetails yashUser = User.builder()
                .username("yash")
                .password(passwordEncoder.encode("soni"))
                .roles(EMPLOYEE.name())
                    .build();

            UserDetails himanshu = User.builder()
                    .username("himanshu")
                    .password(passwordEncoder.encode("verma"))
                    .roles(ADMIN.name())
                    .build();
      return new InMemoryUserDetailsManager(
              yashUser,
              himanshu
      );

    }
}
