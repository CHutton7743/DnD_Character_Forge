package com.DnDForge.Crucible.Forge.Exceptions;

public class CharacterNotFoundException extends RuntimeException{
    public CharacterNotFoundException(long id) {
        super("Could not find character with id: " + id);
    }
}
