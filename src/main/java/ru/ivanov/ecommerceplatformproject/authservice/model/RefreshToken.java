package ru.ivanov.ecommerceplatformproject.authservice.model;

//@Entity
//@NoArgsConstructor
//@Getter
//@Table(name = "refresh_tokens")
public class RefreshToken {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//
//    @Column(name = "subject_id", nullable = false)
//    private UUID subjectId;
//
//    @Column(name = "token", nullable = false, unique = true)
//    private String token;
//
//    @Column(name = "expiration_date", nullable = false)
//    private Instant expirationDate;
//
//    @Column(name = "revoked", nullable = false)
//    private boolean revoked = false;
//
//    @Column(name = "revoked_at")
//    private Instant revokedAt;
//
//    @Column(name = "created_at", nullable = false)
//    private Instant createdAt;
//
//    public RefreshToken(UUID subjectId, String token, Instant expirationDate) {
//        this.token = token;
//        this.subjectId = subjectId;
//        this.expirationDate = expirationDate;
//        this.createdAt = Instant.now();
//    }
//
//    public void revoke() {
//        this.revoked = true;
//        this.revokedAt = Instant.now();
//    }
//
//    public boolean isExpired() {
//        return Instant.now().isAfter(expirationDate);
//    }
}