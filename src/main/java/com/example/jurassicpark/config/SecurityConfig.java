package com.example.jurassicpark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF para pruebas (habilítalo en producción)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configura CORS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll() // Rutas públicas
                        .anyRequest().authenticated() // Rutas protegidas
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de inicio de sesión personalizada
                        .loginProcessingUrl("/api/login") // Ruta donde se procesa el login
                        .defaultSuccessUrl("/home", true) // Redirigir al home tras inicio exitoso
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout") // URL para cerrar sesión
                        .logoutSuccessUrl("/login") // Redirigir al login tras logout
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("http://localhost:3000"); // Permitir todos los orígenes (ajusta en producción)
        configuration.addAllowedMethod("*"); // Permitir todos los métodos HTTP
        configuration.addAllowedHeader("*"); // Permitir todos los headers
        configuration.setAllowCredentials(true); // Permitir credenciales (cookies, headers de autenticación, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
