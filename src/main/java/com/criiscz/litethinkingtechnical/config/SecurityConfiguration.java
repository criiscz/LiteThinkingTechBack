package com.criiscz.litethinkingtechnical.config;

import com.criiscz.litethinkingtechnical.config.cognitoAuth.UserAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final String issuerUri;
    private final UserAuthenticationFilter userAuthenticationFilter;

    @Autowired
    public SecurityConfiguration(@Value("${cognito.issuer-uri}") String issuerUri,
                                 UserAuthenticationFilter userAuthenticationFilter) {
        this.issuerUri = issuerUri;
        this.userAuthenticationFilter = userAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(
                        cors -> cors.configurationSource(request -> {
                            var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                            corsConfiguration.setAllowedOrigins(java.util.List.of("*"));
                            corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                            corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
                            return corsConfiguration;
                        })
                )
                .addFilterAfter(userAuthenticationFilter, BearerTokenAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/health").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/docs/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oAuth2ResourceServerConfigurerCustomizer())
                .build();
    }

    private Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> oAuth2ResourceServerConfigurerCustomizer() {
        final JwtDecoder decoder = JwtDecoders.fromIssuerLocation(issuerUri);
        return (resourceServerConfigurer) -> resourceServerConfigurer.jwt(
                jwtConfigurer -> jwtConfigurer.decoder(decoder)
        );
    }

}
