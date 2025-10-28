package ru.ivanov.ecommerceplatformproject.authservice.util;

import org.springframework.stereotype.Component;

@Component
public class JWTUtils {
//    @Value("${jwt.access.secret}")
//    private String accessTokenSecret;
//
//    @Value("${jwt.access.ttl}")
//    private Duration accessTokenTtl;
//
//    @Value("${jwt.refresh.secret}")
//    private String refreshTokenSecret;
//
//    @Value("${jwt.refresh.ttl}")
//    private Duration refreshTokenTtl;
//
//    @Value("${jwt.service.secret}")
//    private String serviceTokenSecret;
//
//    @Value("${jwt.service.ttl}")
//    private Duration serviceTokenTtl;
//
//    @Value("${jwt.issuer}")
//    private String issuer;
//
//
//    public String generateAccessToken(BaseDto dto) {
//        Instant now = Instant.now();
//        return Jwts.builder()
//                .header()
//                .type("JWT")
//                .and()
//                .id(UUID.randomUUID().toString())
//                .claim("type", "access")
//                .subject(dto.id().toString())
//                .claim("roles", dto.roles())
//                .issuedAt(Date.from(now))
//                .expiration(Date.from(now.plus(accessTokenTtl)))
//                .issuer(issuer)
//                .signWith(getSigningKey(accessTokenSecret))
//                .compact();
//    }
//
//    public RefreshToken generateRefreshToken(BaseDto dto) {
//        UUID subjectId = dto.id();
//        Instant now = Instant.now();
//        Instant expirationDate = now.plus(refreshTokenTtl);
//        String token = Jwts.builder()
//                .header()
//                .type("JWT")
//                .and()
//                .id(UUID.randomUUID().toString())
//                .claim("type", "refresh")
//                .subject(dto.id().toString())
//                .issuedAt(Date.from(now))
//                .expiration(Date.from(expirationDate))
//                .issuer(issuer)
//                .signWith(getSigningKey(refreshTokenSecret))
//                .compact();
//        return new RefreshToken(subjectId, token, expirationDate);
//    }
//
//    public String generateServiceToken(UUID subjectId) {
//        Instant now = Instant.now();
//        return Jwts.builder()
//                .header()
//                .type("JWT")
//                .and()
//                .id(UUID.randomUUID().toString())
//                .claim("type", "service")
//                .claim("subjectId", subjectId)
//                .claim("roles", List.of("ROLE_AUTH_SERVICE"))
//                .issuedAt(Date.from(now))
//                .expiration(Date.from(now.plus(serviceTokenTtl)))
//                .issuer(issuer)
//                .signWith(getSigningKey(serviceTokenSecret))
//                .compact();
//    }
//
//    private SecretKey getSigningKey(String tokenSecret) {
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenSecret));
//    }
//
//    public boolean isAccessToken(String token) {
//        try {
//            Claims claims = Jwts.parser()
//                    .verifyWith(getSigningKey(accessTokenSecret))
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload();
//
//            return claims.get("type").equals("access");
//        } catch (ExpiredJwtException e) {
//            // Просроченный токен все еще считается access-токеном
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    public UUID validateRefreshTokenAndExtractSubjectId(String token) {
//        Claims claims = validateAndParseRefreshToken(token);
//        return UUID.fromString(claims.getSubject());
//    }
//
//    public Claims validateAndParseRefreshToken(String token) {
//        try {
//            return Jwts.parser()
//                    .verifyWith(getSigningKey(refreshTokenSecret))
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload();
//
//        } catch (ExpiredJwtException e) {
//            throw new JwtException("Refresh token expired", e);
//        } catch (UnsupportedJwtException e) {
//            throw new JwtException("Unsupported JWT format", e);
//        } catch (MalformedJwtException e) {
//            throw new JwtException("Malformed JWT", e);
//        } catch (SignatureException e) {
//            throw new JwtException("Invalid signature", e);
//        } catch (IllegalArgumentException e) {
//            throw new JwtException("Invalid token", e);
//        }
//    }
//
//    public Claims validateAndParseAccessToken(String token) {
//        try {
//            return Jwts.parser()
//                    .verifyWith(getSigningKey(accessTokenSecret))
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload();
//
//        } catch (ExpiredJwtException e) {
//            throw new JwtException("Access token expired", e);
//        } catch (UnsupportedJwtException e) {
//            throw new JwtException("Unsupported JWT format", e);
//        } catch (MalformedJwtException e) {
//            throw new JwtException("Malformed JWT", e);
//        } catch (SignatureException e) {
//            throw new JwtException("Invalid signature", e);
//        } catch (IllegalArgumentException e) {
//            throw new JwtException("Invalid token", e);
//        }
//    }
}