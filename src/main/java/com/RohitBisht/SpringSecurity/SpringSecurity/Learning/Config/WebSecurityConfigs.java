package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Config;


import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Filters.JwtAuthFilter;
//import com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Filters.LoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigs {

//    private final LoggingFilter loggingFilter;
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/post", "/auth/**").permitAll()
                        .requestMatchers("/post/**").authenticated()
                        .anyRequest()
                        .authenticated()
                )
                .csrf(csrfConfig -> csrfConfig.disable()) //->To remove the csrf token
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                //.addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class)//Commenting for some time because adding advanced security features
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                //.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

//    @Bean
//    UserDetailsService myInMemoryUSerDetailService() {
//        UserDetails normalUser = User
//                .withUsername("Ram")
//                .password(passwordEncoder().encode("Rohit"))
//                .roles("USER")
//                .build();
//
//
//        UserDetails adminUser = User
//                .withUsername("Pawan")
//                .password(passwordEncoder().encode("Pawan"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails normalUser2 = User
//                .withUsername("Shivam Upadhyay")
//                .password(passwordEncoder().encode("Shivam"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, normalUser2, adminUser);
//    }



    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
