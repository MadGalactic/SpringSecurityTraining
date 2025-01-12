package com.madgalactic.SpringSecurityTraining.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


/*
    By default, spring security provides a filter chain. If you wanted to customize your own, you would need to do so
    by creating a Config class. When you want to customize something in the Spring framework, you create a separate config class
    and create Beans. This will inject the object

 */
@Configuration
@EnableWebSecurity /* To avoid implementing the default Spring security configurations, use this annotation. It is basically saying ignore the default flow, follow this flow instead */
public class SecurityConfig {

    /* This method returns the object of security filter chain*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //disable csrf
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); /* require authentication*/
//        http.formLogin(Customizer.withDefaults()); /* form login implementation*/
        http.httpBasic(Customizer.withDefaults()); /* for rest api access */
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build(); /* the build method allows you to return the security filter chain */
    }


}
