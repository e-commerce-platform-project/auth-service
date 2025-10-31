package ru.ivanov.ecommerceplatformproject.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
//    private String issuerUri;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(
//            HttpSecurity http, JwtAuthenticationConverter jwtAuthenticationConverter, JwtDecoder decoder
//    ) throws Exception {
//        return http
//                .cors(AbstractHttpConfigurer::disable)
//                .csrf(AbstractHttpConfigurer::disable)
////                .exceptionHandling(exceptionHandling ->
////                        exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/api/v1/auth/register").permitAll()
//                                .requestMatchers("/api/v1/auth/resend-verification-code").permitAll()
//                                .requestMatchers("/api/v1/auth/login").permitAll()
//                                .requestMatchers("/api/v1/auth/refresh-token").permitAll()
////                        .requestMatchers(POST, "/api/v1/auth/user/register").permitAll()
////                        .requestMatchers(POST, "/api/v1/auth/seller/register").permitAll()
////                        .requestMatchers(POST, "/api/v1/auth/user/login").permitAll()
////                        .requestMatchers(POST, "/api/v1/auth/seller/login").permitAll()
////                        .requestMatchers(POST, "/api/v1/auth/user/refresh").permitAll()
////                        .requestMatchers(POST, "/api/v1/auth/seller/refresh").permitAll()
////                        .requestMatchers(POST, "/api/v1/auth/logout").permitAll()
////                        .requestMatchers(GET,"/swagger-ui/**", "/v3/api-docs/**", "/actuator/health").permitAll()
////                        .anyRequest().denyAll()
//                                .anyRequest().authenticated()
//
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwtConfigurer -> jwtConfigurer
//                                .decoder(decoder)
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter))
//                )
//                .build();
//    }


//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of("*"));
//        config.setAllowedMethods(List.of("*"));
//        config.setAllowedHeaders(List.of("*"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        return JwtDecoders.fromIssuerLocation(issuerUri);
//    }

//    @Bean
//    public Converter<Jwt, Collection<GrantedAuthority>> keycloakJwtGrantedAuthoritiesConverter() {
//        return jwt -> {
//            List<String> roles = jwt.getClaimAsStringList("roles");
//            if (CollectionUtils.isEmpty(roles)) {
//                return Collections.emptyList();
//            }
//
//            return roles.stream()
//                    .map(SimpleGrantedAuthority::new)
//                    .map(GrantedAuthority.class::cast)
//                    .toList();
//        };
//    }


//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter(
//            Converter<Jwt, Collection<GrantedAuthority>> keycloakJwtGrantedAuthoritiesConverter
//    ) {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakJwtGrantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
}