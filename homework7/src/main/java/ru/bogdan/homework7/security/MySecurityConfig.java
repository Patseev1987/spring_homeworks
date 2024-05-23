package ru.bogdan.homework7.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/tasks").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/add").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/delete/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/status/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated()
                ).logout(logout -> logout.logoutUrl("/api"))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsManager inMemoryUserDetailsManager() {
        var user = User.withUsername("user").password("{noop}user").roles("USER").build();
        var admin = User.withUsername("admin").password("{noop}admin").roles("USER", "ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}

