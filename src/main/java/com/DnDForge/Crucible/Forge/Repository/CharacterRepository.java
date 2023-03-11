package com.DnDForge.Crucible.Forge.Repository;

import com.DnDForge.Crucible.Forge.Entity.AppUser;
import com.DnDForge.Crucible.Forge.Entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findAllByUser(AppUser user);
}
