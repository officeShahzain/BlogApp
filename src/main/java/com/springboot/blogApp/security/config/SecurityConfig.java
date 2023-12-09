package com.springboot.blogApp.security.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetails customUserDetails;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request)->
                        request.requestMatchers("user/public")
                            .permitAll()
                .anyRequest()
                .authenticated());
        return http.build();
    }
    //

    public void configure (AuthenticationManagerBuilder auth)throws Exception
    {
        auth.userDetailsService(customUserDetails).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}

        /*http

                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("user/public")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
        return http.build();
//                .anyRequest().authenticated().and().httpBasic();

         */

