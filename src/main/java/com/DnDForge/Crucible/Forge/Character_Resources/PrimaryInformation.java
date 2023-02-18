package com.DnDForge.Crucible.Forge.Character_Resources;

import lombok.Data;

@Data
public class PrimaryInformation {
    String name;
    Race race;
    Class characterClass;
    int characterLevel;
    int experience;
    Alignment alignment;
    String background;
}
