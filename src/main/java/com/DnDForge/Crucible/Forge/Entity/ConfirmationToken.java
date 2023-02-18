package com.DnDForge.Crucible.Forge.Entity;

import com.DnDForge.Crucible.Forge.Entity.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@AllArgsConstructor
@Table(name = "confirmation_token")
public class ConfirmationToken {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name ="token", nullable = false)
    private String token;
    @Column(name = "time_created", nullable = false)
    private LocalDateTime createdAt;
    @Column(name= "time_expires", nullable = false)
    private LocalDateTime expiresAt;
    @Column(name = "time_confirmed")
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private AppUser user;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, AppUser user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public ConfirmationToken() {

    }
}
