package com.psi2.donaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.filter.CorsFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final RateLimitFilter rateLimitFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtUtil jwtUtil,
                          RateLimitFilter rateLimitFilter,
                          @Lazy UserDetailsService usuarioService) {
        this.jwtUtil = jwtUtil;
        this.rateLimitFilter = rateLimitFilter;
        this.userDetailsService = usuarioService;
    }
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, userDetailsService); 
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
              //  .requestMatchers(HttpMethod.POST, "/usuarios/register", "/api/usuarios/register").permitAll()
               /* .requestMatchers(
                    "/auth/**",
                    "/metricas",
                    "/solicitudes/aprobadas/almacen",
                    "/solicitudes/aceptar-ayuda/**",
                    "/solicitudes/enviar-info-voluntario/**",
                    "/solicitudes-sin-responder/inventario",
                    "/donaciones/donantes",
                    "/donaciones/armado/**",
                    "/usuarios/**",
                    "/solicitudes-sin-responder/crear-completa",
                    "/solicitudes/apoyo",
                    "/images/**",
                    "/ws/**",
                    "/proxy/**",
                    "/actuator/**", "/actuator/info"
                ).permitAll()*/
                //.anyRequest().authenticated()     
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()           
                .anyRequest().permitAll()
            )
           // .addFilterBefore(rateLimitFilter, UsernamePasswordAuthenticationFilter.class)
          //  .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
/**
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        // Add all frontends you use (localhost, LAN IP, prod domain)
        cfg.setAllowedOrigins(List.of(
            "http://192.168.0.18:3000",
            "http://192.168.0.18:8082",
            "http://localhost:3000",
            "http://127.0.0.1:3000",
            "http://127.0.0.1:8082",
            "http://das-front.local"

        ));
        cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        cfg.setAllowedHeaders(List.of("*"));
        cfg.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }
 */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
      //  cfg.setAllowedOrigins(List.of("http://192.168.0.18:3000", "http://das-front.local"));
        cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));
        cfg.addAllowedHeader("*");
        cfg.setAllowCredentials(false);
        cfg.addAllowedOriginPattern("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
  

}
