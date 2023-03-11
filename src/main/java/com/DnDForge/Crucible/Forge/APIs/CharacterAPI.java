package com.DnDForge.Crucible.Forge.APIs;

import com.DnDForge.Crucible.Forge.Exceptions.CharacterNotFoundException;
import com.DnDForge.Crucible.Forge.Entity.AppUser;
import com.DnDForge.Crucible.Forge.Entity.Character;
import com.DnDForge.Crucible.Forge.Repository.CharacterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/characters")
@Transactional
public class CharacterAPI {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or @characterSecurityService.canAccessCharacter(authentication,#id)")
    public ResponseEntity<List<Character>> getAllCharacters(AppUser user) {
        if (user == null) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if (characterRepository.findAllByUser(user).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Character> characters = characterRepository.findAllByUser(user);
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/adminGetAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        return new ResponseEntity<>(characters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@characterSecurityService.canAccessCharacter(authentication,#id)")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
        return new ResponseEntity<>(character, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        Character newCharacter = characterRepository.save(character);
        return new ResponseEntity<>(newCharacter, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@characterSecurityService.canAccessCharacter(authentication,#id)")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character character) {
        Character updatedCharacter = characterRepository.findById(id)
                .map(c -> {
                    c.setUser(character.getUser());
                    c.setFile(character.getFile());
                    c.setId(character.getId());
                    return characterRepository.save(c);
                })
                .orElseThrow(() -> new CharacterNotFoundException(id));
        return new ResponseEntity<>(updatedCharacter, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@characterSecurityService.canAccessCharacter(authentication,#id)")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id, Authentication authentication) {
        AppUser user = (AppUser) authentication.getPrincipal();
        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));
        if (user.getRole().equals("ADMIN") || character.getUser().getUser_id() == user.getUser_id()) {
            characterRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
