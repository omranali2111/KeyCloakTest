package com.KeyCloakTest.KeyCloakTest.Configration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity //this used to Enable role based Auth
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwk;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        //.requestMatchers(HttpMethod.POST, "/**").hasRole("Client_Admin")
                        .anyRequest().authenticated()

                );
        http
                 .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> {
                    jwt.decoder(jwtDecoder(jwk));
                    jwt.jwtAuthenticationConverter(jwtAuthConverter);
                    })
                 );





        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }
    @Bean
    public JwtDecoder jwtDecoder(@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}") String jwkSetUri) {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

}
