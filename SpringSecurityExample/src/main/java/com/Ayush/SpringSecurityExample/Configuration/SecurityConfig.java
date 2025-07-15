package com.Ayush.SpringSecurityExample.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
=======
>>>>>>> 52dbf68a86f4b3820a2f3c391708768c91a2dd9e
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

<<<<<<< HEAD
//    private final UserDetailsService userDetailsService;
//
//    public SecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

=======
>>>>>>> 52dbf68a86f4b3820a2f3c391708768c91a2dd9e
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();

//        return httpSecurity.build();
    }
<<<<<<< HEAD

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("rajesh")
                .password("{noop}raj")  // Use {noop} or encoder.encode()
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("nisha")
                .password("{noop}ni")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
=======
>>>>>>> 52dbf68a86f4b3820a2f3c391708768c91a2dd9e
}
