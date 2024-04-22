package com.gsb_appart.gsb_appart.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/inscription", "/inscriptionConfirmation").permitAll()  // Ensured paths are correctly separated by commas
                        .requestMatchers("/login", "/js/**", "/css/**", "/img/**", "/public/**").permitAll()  // Simplified to avoid redundant paths
                        .requestMatchers("/louer").authenticated()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/proprietaire/**").hasAuthority("ROLE_PROPRIETAIRE")
                        .requestMatchers("/locataire/**").hasAuthority("ROLE_LOCATAIRE")
                        .requestMatchers("/profil", "/appartements/lister").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .defaultSuccessUrl("/profil", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")  // Updated to match the publicly accessible URL
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
