package com.github.michele.cianni.gradesubmission.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.github.michele.cianni.gradesubmission.security.filter.CustomAuthenticationFilter;
import com.github.michele.cianni.gradesubmission.security.filter.ExceptionHandlerFilter;
import com.github.michele.cianni.gradesubmission.security.filter.JWTAuthorizationFilter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager);
          authenticationFilter.setFilterProcessesUrl("/auth");
        http
                .csrf(config -> config.disable())
                .headers(config -> config.frameOptions(FrameOptionsConfig::disable))
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new ExceptionHandlerFilter(), CustomAuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), CustomAuthenticationFilter.class)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
