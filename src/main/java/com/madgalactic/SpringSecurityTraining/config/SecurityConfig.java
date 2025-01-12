package com.madgalactic.SpringSecurityTraining.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


/*
    By default, spring security provides a filter chain. If you wanted to customize your own, you would need to do so
    by creating a Config class. When you want to customize something in the Spring framework, you create a separate config class
    and create Beans. This will inject the object

 */
@Configuration
@EnableWebSecurity /* To avoid implementing the default Spring security configurations, use this annotation. It is basically saying ignore the default flow, follow this flow instead */
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    /* This method returns the object of security filter chain*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /* HttpSecurity is an object with several methods, one being csrf.  */

        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .build();

    }

    //create a bean to change the authentication provider

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;


    }
//    @Bean
//    public UserDetailsService userDetailsService () {
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("jane")
//                .password("abc123")
//                .roles("USER")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user1);

}
