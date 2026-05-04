package myapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiration;

    @ManyToOne(optional = false)
    @JoinColumn(name = "membre_id")
    private Member membre;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, LocalDateTime expiration, Member membre) {
        this.token = token;
        this.expiration = expiration;
        this.membre = membre;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public Member getMembre() {
        return membre;
    }

    public boolean isExpired() {
        return expiration.isBefore(LocalDateTime.now());
    }
}