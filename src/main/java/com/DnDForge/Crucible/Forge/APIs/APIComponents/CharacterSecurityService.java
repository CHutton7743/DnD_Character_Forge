package com.DnDForge.Crucible.Forge.APIs.APIComponents;

import com.DnDForge.Crucible.Forge.Entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.DnDForge.Crucible.Forge.Repository.CharacterRepository;
import com.DnDForge.Crucible.Forge.Entity.AppUser;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterSecurityService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterSecurityService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public boolean canAccessCharacter(Long characterId, Authentication authentication) {
        // Get the logged-in user's ID
        long userId = ((AppUser) authentication.getPrincipal()).getUser_id();

        // Get the character with the given ID from the repository
        Optional<Character> optionalCharacter = characterRepository.findById(characterId);

        // If the character doesn't exist, return false
        if (optionalCharacter.isEmpty()) {
            return false;
        }

        Character character = optionalCharacter.get();

        // If the logged-in user is an admin, they can access any character
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return true;
        }

        // Otherwise, only the owner of the character can access it
        return character.getUser().getUser_id() == userId;
    }
}


