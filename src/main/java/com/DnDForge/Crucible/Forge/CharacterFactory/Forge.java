package com.DnDForge.Crucible.Forge.CharacterFactory;

import com.DnDForge.Crucible.Forge.Character_Resources.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Forge {
    public PDDocument createCharacter(PrimaryInformation primaryInformation, BaseStats baseStats, BaseStatModifiers baseStatModifiers,
                                      RPFeatures rpFeatures, Equipment equipment, FeaturesAndTraits featuresAndTraits, OtherProficiencies otherProficiencies, RPFields rpFields) throws IOException {
        PDDocument character = PDDocument.load(new File("src/main/resources/templates/5E_CharacterSheet_Fillable.pdf"));
        character = addPrimaryInformation(character, primaryInformation);
        character = addPrimaryStats(character, baseStats);
        character = addSavingThrows(character, baseStatModifiers);
        character = addRPFields(character, rpFields);
        character = addEquipment(character, equipment);
        character = addFeaturesAndTraits(character, featuresAndTraits);
        character = addOtherProficiencies(character, otherProficiencies);

        return character;
    }
    public PDDocument addPrimaryInformation(PDDocument newCharacter,PrimaryInformation primaryInformation) throws IOException {
        PDAcroForm form = newCharacter.getDocumentCatalog().getAcroForm();
        List<PDField> fields = form.getFields();
        for (PDField field : fields) {
            if(field.getFullyQualifiedName().equals("Character Name")) {
                field.setValue(primaryInformation.getName());
            }
            if(field.getFullyQualifiedName().equals("Class & Level")) {
                field.setValue(primaryInformation.getCharacterClass().toString() + " " + primaryInformation.getCharacterLevel());
            }
            if(field.getFullyQualifiedName().equals("Background")) {
                field.setValue(primaryInformation.getBackground());
            }
            if(field.getFullyQualifiedName().equals("Player Name")) {
                field.setValue(primaryInformation.getName());
            }
            if(field.getFullyQualifiedName().equals("Race")) {
                field.setValue(primaryInformation.getRace().toString());
            }
            if(field.getFullyQualifiedName().equals("Alignment")) {
                field.setValue(primaryInformation.getAlignment().toString());
            }
            if(field.getFullyQualifiedName().equals("Experience Points")) {
                field.setValue(String.valueOf(primaryInformation.getExperience()));
            }
        }
        return newCharacter;
    }

    public PDDocument addPrimaryStats(PDDocument character, BaseStats baseStats) throws IOException {
        PDAcroForm form = character.getDocumentCatalog().getAcroForm();
        List<PDField> fields = form.getFields();
        for (PDField field : fields) {
            if(field.getFullyQualifiedName().equals("Strength")) {
                field.setValue(String.valueOf(baseStats.getStrength()));
            }
            if(field.getFullyQualifiedName().equals("Dexterity")) {
                field.setValue(String.valueOf(baseStats.getDexterity()));
            }
            if(field.getFullyQualifiedName().equals("Constitution")) {
                field.setValue(String.valueOf(baseStats.getConstitution()));
            }
            if(field.getFullyQualifiedName().equals("Intelligence")) {
                field.setValue(String.valueOf(baseStats.getIntelligence()));
            }
            if(field.getFullyQualifiedName().equals("Wisdom")) {
                field.setValue(String.valueOf(baseStats.getWisdom()));
            }
            if(field.getFullyQualifiedName().equals("Charisma")) {
                field.setValue(String.valueOf(baseStats.getCharisma()));
            }
        }
        return character;
    }

    public PDDocument addSavingThrows(PDDocument character, BaseStatModifiers baseStatModifiers) throws IOException {
        PDAcroForm form = character.getDocumentCatalog().getAcroForm();
        List<PDField> fields = form.getFields();
        for (PDField field : fields) {
            if(field.getFullyQualifiedName().equals("Strength Save")) {
                field.setValue(String.valueOf(baseStatModifiers.getStrengthMod()));
            }
            if(field.getFullyQualifiedName().equals("Dexterity Save")) {
                field.setValue(String.valueOf(baseStatModifiers.getDexterityMod()));
            }
            if(field.getFullyQualifiedName().equals("Constitution Save")) {
                field.setValue(String.valueOf(baseStatModifiers.getConstitutionMod()));
            }
            if(field.getFullyQualifiedName().equals("Intelligence Save")) {
                field.setValue(String.valueOf(baseStatModifiers.getIntelligenceMod()));
            }
            if(field.getFullyQualifiedName().equals("Wisdom Save")) {
                field.setValue(String.valueOf(baseStatModifiers.getWisdomMod()));
            }
            if(field.getFullyQualifiedName().equals("Charisma Save")) {
                field.setValue(String.valueOf(baseStatModifiers.getCharismaMod()));
            }
        }
        return character;
    }

    public PDDocument addSpecializedStats(PDDocument character,  SpecializedStats stats) throws IOException {
        PDAcroForm form = character.getDocumentCatalog().getAcroForm();
        List<PDField> fields = form.getFields();
        for (PDField field : fields) {
            if(field.getFullyQualifiedName().equals("Acrobatics")) {
                field.setValue(String.valueOf(stats.getAcrobatics()));
            }
            if(field.getFullyQualifiedName().equals("Animal Handling")) {
                field.setValue(String.valueOf(stats.getAnimalHandling()));
            }
            if(field.getFullyQualifiedName().equals("Arcana")) {
                field.setValue(String.valueOf(stats.getArcana()));
            }
            if(field.getFullyQualifiedName().equals("Athletics")) {
                field.setValue(String.valueOf(stats.getAthletics()));
            }
            if(field.getFullyQualifiedName().equals("Deception")) {
                field.setValue(String.valueOf(stats.getDeception()));
            }
            if(field.getFullyQualifiedName().equals("History")) {
                field.setValue(String.valueOf(stats.getHistory()));
            }
            if(field.getFullyQualifiedName().equals("Insight")) {
                field.setValue(String.valueOf(stats.getInsight()));
            }
            if(field.getFullyQualifiedName().equals("Intimidation")) {
                field.setValue(String.valueOf(stats.getIntimidation()));
            }
            if(field.getFullyQualifiedName().equals("Investigation")) {
                field.setValue(String.valueOf(stats.getInvestigation()));
            }
            if(field.getFullyQualifiedName().equals("Medicine")) {
                field.setValue(String.valueOf(stats.getMedicine()));
            }
            if(field.getFullyQualifiedName().equals("Nature")) {
                field.setValue(String.valueOf(stats.getNature()));
            }
            if(field.getFullyQualifiedName().equals("Perception")) {
                field.setValue(String.valueOf(stats.getPerception()));
            }
            if(field.getFullyQualifiedName().equals("Performance")) {
                field.setValue(String.valueOf(stats.getPerformance()));
            }
            if(field.getFullyQualifiedName().equals("Persuasion")) {
                field.setValue(String.valueOf(stats.getPersuasion()));
            }
            if(field.getFullyQualifiedName().equals("Religion")) {
                field.setValue(String.valueOf(stats.getReligion()));
            }
            if(field.getFullyQualifiedName().equals("Sleight of Hand")) {
                field.setValue(String.valueOf(stats.getSleightOfHand()));
            }
            if(field.getFullyQualifiedName().equals("Stealth")) {
                field.setValue(String.valueOf(stats.getStealth()));
            }
            if(field.getFullyQualifiedName().equals("Survival")) {
                field.setValue(String.valueOf(stats.getSurvival()));
            }
        }
        return character;
    }

    public PDDocument addCombatStats(PDDocument character, CombatFields combatFields) throws IOException {
        PDAcroForm form = character.getDocumentCatalog().getAcroForm();
        List<PDField> fields = form.getFields();
        for (PDField field : fields) {
            if(field.getFullyQualifiedName().equals("Armor Class")) {
                field.setValue(String.valueOf(combatFields.getArmorClass()));
            }
            if(field.getFullyQualifiedName().equals("Initiative")) {
                field.setValue(String.valueOf(combatFields.getInitiative()));
            }
            if(field.getFullyQualifiedName().equals("Speed")) {
                field.setValue(String.valueOf(combatFields.getSpeed()));
            }
            if(field.getFullyQualifiedName().equals("Hit Points")) {
                field.setValue(String.valueOf(combatFields.getHitPoints()));
            }
            if(field.getFullyQualifiedName().equals("Hit Dice")) {
                field.setValue(String.valueOf(combatFields.getHitDice()));
            }
        }
        return character;
    }

    public PDDocument addRPFields(PDDocument character, RPFields fields) throws IOException {
        PDAcroForm form = character.getDocumentCatalog().getAcroForm();
        List<PDField> formFields = form.getFields();
        for (PDField field : formFields) {
            if(field.getFullyQualifiedName().equals("Hair")) {
                field.setValue(fields.getHair());
            }
            if(field.getFullyQualifiedName().equals("Skin")) {
                field.setValue(fields.getSkin());
            }
            if(field.getFullyQualifiedName().equals("Eyes")) {
                field.setValue(fields.getEyes());
            }
            if(field.getFullyQualifiedName().equals("Weight")) {
                field.setValue(String.valueOf(fields.getWeight()));
            }
            if(field.getFullyQualifiedName().equals("Height")) {
                field.setValue(fields.getHeight());
            }
            if(field.getFullyQualifiedName().equals("Age")) {
                field.setValue(String.valueOf(fields.getAge()));
            }
            if(field.getFullyQualifiedName().equals("Character Appearance")) {
                field.setValue(fields.getAppearance());
            }
            if (field.getFullyQualifiedName().equals("Character Backstory")) {
                field.setValue(fields.getBackstory());
            }
        }
        return character;
    }

    public PDDocument addFeaturesAndTraits(PDDocument character, FeaturesAndTraits featuresAndTraits) throws IOException {
        PDAcroForm form = character.getDocumentCatalog().getAcroForm();
        List<PDField> fields = form.getFields();
        for (PDField field : fields) {
            if(field.getFullyQualifiedName().equals("Features and Traits")) {
                for(int i = 0; i < featuresAndTraits.getFeaturesAndTraits().size(); i++) {
                    String currVal = field.getValueAsString();
                    String newTrait = featuresAndTraits.getFeaturesAndTraits().get(i);
                    field.setValue(currVal + "\n" + newTrait);
                }
            }
        }
        return character;
    }

    public PDDocument addEquipment(PDDocument character, Equipment equipment) throws IOException {
        PDAcroForm form = character.getDocumentCatalog().getAcroForm();
        List<PDField> fields = form.getFields();
        for (PDField field : fields) {
            if(field.getFullyQualifiedName().equals("Equipment")) {
                for(int i = 0; i < equipment.getEquipment().size(); i++) {
                    String currVal = field.getValueAsString();
                    String newEquip = equipment.getEquipment().get(i);
                    field.setValue(currVal + "\n" + newEquip);
                }
            }
        }
        return character;
    }

    public PDDocument addOtherProficiencies(PDDocument character, OtherProficiencies otherProficiencies) throws IOException {
        PDAcroForm form = character.getDocumentCatalog().getAcroForm();
        List<PDField> fields = form.getFields();
        for (PDField field : fields) {
            if(field.getFullyQualifiedName().equals("Other Proficiencies and Languages")) {
                for(int i = 0; i < otherProficiencies.getOtherProficiencies().size(); i++) {
                    String currVal = field.getValueAsString();
                    String newProf = otherProficiencies.getOtherProficiencies().get(i);
                    field.setValue(currVal + "\n" + newProf);
                }
            }
        }
        return character;
    }
}
