package com.taxislibres.technicaltest.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET).authenticated()
                                .requestMatchers(HttpMethod.POST).authenticated()
                                .requestMatchers(HttpMethod.PUT).authenticated()
                                .requestMatchers(HttpMethod.DELETE).authenticated()
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
