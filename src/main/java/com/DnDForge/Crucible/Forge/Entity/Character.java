package com.DnDForge.Crucible.Forge.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;

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

    @Lob
    Blob characterSheet;


}
