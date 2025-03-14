package com.example.ott_login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // in real life we should implement a proper user service
        UserDetails user = User.builder()
                .username("admin@localhost.se")
                .password("{noop}no-password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
