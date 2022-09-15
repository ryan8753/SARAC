package com.sarac.sarac.user.config;

import com.sarac.sarac.user.handler.OAuth2AuthenticationSuccessHandler;
import com.sarac.sarac.user.jwt.AuthTokenFilter;
import com.sarac.sarac.user.service.UserService;
import com.sarac.sarac.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@EnableWebSecurity // Spring Security 설정 활성화
@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Autowired
    UserService userService;

    @Autowired
    OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    public SecurityConfig() {
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.cors().configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(Arrays.asList("http://localhost:3000","http://localhost:8080"));
            cors.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
            cors.setAllowedHeaders(Arrays.asList("*"));
            return cors;
        })
                .and().csrf().disable().headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/v1/**").hasAuthority("USER").anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/")
                .and().oauth2Login().successHandler(oAuth2AuthenticationSuccessHandler).userInfoEndpoint().userService(userService).and()
                .and().build();
    }


//                .and().addFilterBefore(new AuthTokenFilter(), UsernamePasswordAuthenticationFilter.class)


}
