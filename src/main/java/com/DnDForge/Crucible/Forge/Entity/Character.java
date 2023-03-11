package com.DnDForge.Crucible.Forge.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Table(name = "characters")
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "character_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = AppUser.class)
    AppUser user;

    String name;
    String characterClass;
    int level;
    String file;

    @CreatedDate
    private LocalDateTime timeCreated;
    @UpdateTimestamp
    private LocalDateTime timeUpdated;


}
