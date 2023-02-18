package com.DnDForge.Crucible.Forge.Character_Resources;

import lombok.Data;

@Data
public class BaseStatModifiers {
    private int strengthMod;
    private int dexterityMod;
    private int constitutionMod;
    private int intelligenceMod;
    private int wisdomMod;
    private int charismaMod;

    public BaseStatModifiers(BaseStats baseStats) {
        this.strengthMod = calculateModifier(baseStats.getStrength());
        this.dexterityMod = calculateModifier(baseStats.getDexterity());
        this.constitutionMod = calculateModifier(baseStats.getConstitution());
        this.intelligenceMod = calculateModifier(baseStats.getIntelligence());
        this.wisdomMod = calculateModifier(baseStats.getWisdom());
        this.charismaMod = calculateModifier(baseStats.getCharisma());
    }

    public static int calculateModifier(int baseNumber) {
        int modifier = 0;
        switch(baseNumber) {
            case 1:
                modifier = -5;
                break;
            case 2: case 3:
                modifier = -4;
                break;
            case 4: case 5:
                modifier = -3;
                break;
            case 6: case 7:
                modifier = -2;
                break;
            case 8: case 9:
                modifier = -1;
                break;
            case 10: case 11:
                modifier = 0;
                break;
            case 12: case 13:
                modifier = 1;
                break;
            case 14: case 15:
                modifier = 2;
                break;
            case 16: case 17:
                modifier = 3;
                break;
            case 18: case 19:
                modifier = 4;
                break;
            default:
                modifier = 5;
                break;
        }
        return modifier;
    }

}
