package com.DnDForge.Crucible.Forge.Character_Resources;

public class Dice {
    public int diceRoll(int diesides) {
        // Diceroll >= 1
        int lowerLimit=1;
        return (int)Math.floor(Math.random()*(diesides-lowerLimit+1)+lowerLimit);
    }
}