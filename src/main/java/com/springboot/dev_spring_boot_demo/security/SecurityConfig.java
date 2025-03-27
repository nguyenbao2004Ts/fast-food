package com.springboot.dev_spring_boot_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails truong = User.builder()
//                .username("truong")
//                .password("{noop}truong")
//                .roles("STUDENT")
//                .build();
//        UserDetails tru=User.builder()
//                .username("tru")
//                .password("{noop}tru")
//                .roles("STUDENT","MANAGER")
//                .build();
//        UserDetails bao=User.builder()
//                .username("bao")
//                .password("{noop}bao")
//                .roles("MANAGER","STUDENT","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(bao);
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers("/admin").hasRole("MANAGER")
                        .requestMatchers("/system").hasRole("ADMIN")
                        .requestMatchers("/foods").hasRole("ADMIN")
                        .requestMatchers("/customer/**").hasRole("ADMIN")
//                        .requestMatchers("/user/form-insert").permitAll()
//                        .requestMatchers("/user").permitAll()
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
        ).formLogin(form->
                form.loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        )
                .logout(logout->logout.permitAll()
        )
                .exceptionHandling(configurer->
                        configurer.accessDeniedPage("/error")
                );
        return http.build();
    }
}
