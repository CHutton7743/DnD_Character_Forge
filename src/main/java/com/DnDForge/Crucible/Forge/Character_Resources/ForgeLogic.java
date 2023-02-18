//package com.DnDForge.Crucible.Forge.Character_Resources;
//
//import lombok.Data;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//@Component
//@Data
//public class ForgeLogic {
//    private ArrayList<Integer> statsList = new ArrayList<>();
//    private int[] statArray = new int[6];
//    private int[] subStatArray = new int[6];
//
//    public ArrayList<Integer> rollStats4d6() {
//        Dice dice = new Dice();
//        ArrayList<Integer> statList = new ArrayList<>();
//        int[] rolls = new int[4];
//        int test = 0;
//        for(int i = 0; i < 6; i ++) {
//            for(int k = 0; k < 4; k++) {
//                int stat = dice.diceRoll(6);
//                rolls[k] = stat;
//            }
//            Arrays.sort(rolls);
//            rolls[0] = 0;
//            for (int l = 0; l < rolls.length; l++) {
//                test += rolls[l];
//                rolls[l] = 0;
//            }
//            statList.add(test);
//            test = 0;
//        }
//        int total = 0;
//        for (Integer integer : statList) {
//            total += integer;
//        }
//        if (total < 72) {
//            rollStats4d6();
//        }
//        return statList;
//    }
//    public ArrayList<Integer> standardStatList() {
//        ArrayList<Integer> statList = new ArrayList<>();
//        statList.add(15);
//        statList.add(14);
//        statList.add(13);
//        statList.add(12);
//        statList.add(10);
//        statList.add(8);
//        return statList;
//    }
//    public void formatStats(Character character) {
//        this.statsList.set(0, character.getStrength());
//        this.statsList.set(1, character.getDexterity());
//        this.statsList.set(2, character.getConstitution());
//        this.statsList.set(3, character.getIntelligence());
//        this.statsList.set(4, character.getWisdom());
//        this.statsList.set(5, character.getCharisma());
//    }
//
//    public void calculateRacialStats(Character character, String halfElfChoice) {
//        switch(character.getRace()) {
//            case Elf -> {
//                if (character.getSubrace() == SubRace.HighElf) {
//                    character.setIntelligence(character.getIntelligence()+1);
//                    character.setDexterity(character.getDexterity()+2);
//                } else if (character.getSubrace() == SubRace.WoodElf) {
//                    character.setConstitution(character.getConstitution()+2);
//                    character.setIntelligence(character.getIntelligence()+1);
//                } else if (character.getSubrace() == SubRace.Drow) {
//                    character.setDexterity(character.getDexterity() +2);
//                    character.setCharisma(character.getCharisma()+1);
//                } else {
//                    character.setIntelligence(character.getIntelligence()+1);
//                    character.setDexterity(character.getDexterity()+2);
//                }
//            }
//            case Orc, Halforc -> {
//                character.setStrength(character.getStrength()+2);
//                character.setConstitution(character.getConstitution()+1);
//            }
//            case Gnome -> {
//                if (character.getSubrace() == SubRace.DeepGnome) {
//                    character.setIntelligence(character.getIntelligence()+2);
//                    character.setDexterity(character.getDexterity()+1);
//                } else if (character.getSubrace() == SubRace.RockGnome) {
//                    character.setConstitution(character.getConstitution()+1);
//                    character.setIntelligence(character.getIntelligence()+2);
//                } else if (character.getSubrace() == SubRace.ForestGnome) {
//                    character.setIntelligence(character.getIntelligence()+2);
//                    character.setConstitution(character.getConstitution()+1);
//                } else {
//                    character.setIntelligence(character.getIntelligence()+2);
//                    character.setWisdom(character.getWisdom()+1);
//                }
//            }
//            case Halfling -> {
//                if (character.getSubrace() == SubRace.StoutHalfling) {
//                    character.setDexterity(character.getDexterity()+2);
//                    character.setConstitution(character.getConstitution()+1);
//                } else if (character.getSubrace() == SubRace.LightFootHalfling) {
//                    character.setDexterity(character.getDexterity()+2);
//                    character.setCharisma(character.getCharisma()+1);
//                } else {
//                    character.setDexterity(character.getDexterity()+2);
//                    character.setConstitution(character.getConstitution()+1);
//                }
//            }
//            case Dwarf -> {
//                if (character.getSubrace() == SubRace.GrayDwarf) {
//                    character.setConstitution(character.getConstitution()+2);
//                    character.setStrength(character.getStrength()+1);
//                } else if (character.getSubrace() == SubRace.HillDwarf) {
//                    character.setConstitution(character.getConstitution()+2);
//                    character.setWisdom(character.getWisdom()+1);
//                } else if (character.getSubrace() == SubRace.MountainDwarf) {
//                    character.setStrength(character.getStrength()+2);
//                    character.setConstitution(character.getConstitution()+2);
//                } else {
//                    character.setStrength(character.getStrength()+2);
//                    character.setConstitution(character.getConstitution()+1);
//                }
//            }
//            case Dragonborn -> {
//                character.setStrength(character.getStrength());
//                character.setCharisma(character.getCharisma());
//            }
//            case Tiefling -> {
//                character.setCharisma(character.getCharisma());
//                character.setIntelligence(character.getIntelligence());
//            }
//            case Halfelf -> {
//                character.setCharisma(character.getCharisma()+2);
//                String choice = halfElfChoice.toLowerCase();
//                switch (choice) {
//                    case "intellect" -> character.setIntelligence(character.getIntelligence()+1);
//                    case "strength" ->  character.setStrength(character.getStrength()+1);
//                    case "dexterity" -> character.setDexterity(character.getDexterity()+1);
//                    case "wisdom" -> character.setWisdom(character.getWisdom()+1);
//                    case "constitution" -> character.setConstitution(character.getConstitution()+1);
//                    case "charisma" -> character.setCharisma(character.getCharisma()+1);
//                }
//            }
//            case Human -> {
//                character.setCharisma(character.getCharisma()+1);
//                character.setStrength(character.getStrength()+1);
//                character.setWisdom(character.getWisdom()+1);
//                character.setDexterity(character.getDexterity()+1);
//                character.setConstitution(character.getConstitution()+1);
//                character.setIntelligence(character.getIntelligence()+1);
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + character.getRace());
//        }
//    }
//    public void calculateRacialStats(Character character) {
//        switch(character.getRace()) {
//            case Elf -> {
//                if (character.getSubrace() == SubRace.HighElf) {
//                    character.setIntelligence(character.getIntelligence()+1);
//                    character.setDexterity(character.getDexterity()+2);
//                } else if (character.getSubrace() == SubRace.WoodElf) {
//                    character.setConstitution(character.getConstitution()+2);
//                    character.setIntelligence(character.getIntelligence()+1);
//                } else if (character.getSubrace() == SubRace.Drow) {
//                    character.setDexterity(character.getDexterity() +2);
//                    character.setCharisma(character.getCharisma()+1);
//                } else {
//                    character.setIntelligence(character.getIntelligence()+1);
//                    character.setDexterity(character.getDexterity()+2);
//                }
//            }
//            case Orc, Halforc -> {
//                character.setStrength(character.getStrength()+2);
//                character.setConstitution(character.getConstitution()+1);
//            }
//            case Gnome -> {
//                if (character.getSubrace() == SubRace.DeepGnome) {
//                    character.setIntelligence(character.getIntelligence()+2);
//                    character.setDexterity(character.getDexterity()+1);
//                } else if (character.getSubrace() == SubRace.RockGnome) {
//                    character.setConstitution(character.getConstitution()+1);
//                    character.setIntelligence(character.getIntelligence()+2);
//                } else if (character.getSubrace() == SubRace.ForestGnome) {
//                    character.setIntelligence(character.getIntelligence()+2);
//                    character.setConstitution(character.getConstitution()+1);
//                } else {
//                    character.setIntelligence(character.getIntelligence()+2);
//                    character.setWisdom(character.getWisdom()+1);
//                }
//            }
//            case Halfling -> {
//                if (character.getSubrace() == SubRace.StoutHalfling) {
//                    character.setDexterity(character.getDexterity()+2);
//                    character.setConstitution(character.getConstitution()+1);
//                } else if (character.getSubrace() == SubRace.LightFootHalfling) {
//                    character.setDexterity(character.getDexterity()+2);
//                    character.setCharisma(character.getCharisma()+1);
//                } else {
//                    character.setDexterity(character.getDexterity()+2);
//                    character.setConstitution(character.getConstitution()+1);
//                }
//            }
//            case Dwarf -> {
//                if (character.getSubrace() == SubRace.GrayDwarf) {
//                    character.setConstitution(character.getConstitution()+2);
//                    character.setStrength(character.getStrength()+1);
//                } else if (character.getSubrace() == SubRace.HillDwarf) {
//                    character.setConstitution(character.getConstitution()+2);
//                    character.setWisdom(character.getWisdom()+1);
//                } else if (character.getSubrace() == SubRace.MountainDwarf) {
//                    character.setStrength(character.getStrength()+2);
//                    character.setConstitution(character.getConstitution()+2);
//                } else {
//                    character.setStrength(character.getStrength()+2);
//                    character.setConstitution(character.getConstitution()+1);
//                }
//            }
//            case Dragonborn -> {
//                character.setStrength(character.getStrength());
//                character.setCharisma(character.getCharisma());
//            }
//            case Tiefling -> {
//                character.setCharisma(character.getCharisma());
//                character.setIntelligence(character.getIntelligence());
//            }
//            case Human -> {
//                character.setCharisma(character.getCharisma()+1);
//                character.setStrength(character.getStrength()+1);
//                character.setWisdom(character.getWisdom()+1);
//                character.setDexterity(character.getDexterity()+1);
//                character.setConstitution(character.getConstitution()+1);
//                character.setIntelligence(character.getIntelligence()+1);
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + character.getRace());
//        }
//    }
//
//    public void calculateSize(Character character) {
//        switch(character.getRace()) {
//            case Elf, Tiefling, Orc, Dragonborn, Human, Halfelf, Halforc -> character.setSize(Size.Medium);
//            case Gnome, Halfling, Dwarf -> character.setSize(Size.Small);
//            default -> throw new IllegalStateException("Unexpected value: " + character.getRace());
//        }
//    }
//
//    public void calculateStrengthSubstats(Character character) {
//        int test = statsList.get(0);
//        switch (test) {
//            case 1 -> character.setAthletics(character.getAthletics()-5);
//            case 2, 3 -> character.setAthletics(character.getAthletics()-4);
//            case 4, 5 -> character.setAthletics(character.getAthletics()-3);
//            case 6, 7 -> character.setAthletics(character.getAthletics()-2);
//            case 8, 9 -> character.setAthletics(character.getAthletics()-1);
//            case 10, 11 -> character.setAthletics(character.getAthletics());
//            case 12, 13 -> character.setAthletics(character.getAthletics()+1);
//            case 14, 15 -> character.setAthletics(character.getAthletics()+2);
//            case 16, 17 -> character.setAthletics(character.getAthletics()+3);
//            case 18 -> character.setAthletics(character.getAthletics()+4);
//            default -> throw new IllegalStateException("Unexpected value: " + test);
//        }
//    }
//    public void calculateDexteritySubstats(Character character) {
//        int test = statsList.get(1);
//        switch (test) {
//            case 1 -> {
//                character.setAcrobatics(character.getAcrobatics()-5);
//                character.setStealth(character.getStealth()-5);
//                character.setSleightOfHand(character.getSleightOfHand()-5);
//            }
//            case 2, 3 -> {
//                character.setAcrobatics(character.getAcrobatics()-4);
//                character.setStealth(character.getStealth()-4);
//                character.setSleightOfHand(character.getSleightOfHand()-4);
//            }
//            case 4, 5 -> {
//                character.setAcrobatics(character.getAcrobatics()-3);
//                character.setStealth(character.getStealth()-3);
//                character.setSleightOfHand(character.getSleightOfHand()-3);
//            }
//            case 6, 7 -> {
//                character.setAcrobatics(character.getAcrobatics()-2);
//                character.setStealth(character.getStealth()-2);
//                character.setSleightOfHand(character.getSleightOfHand()-2);
//            }
//            case 8, 9 -> {
//                character.setAcrobatics(character.getAcrobatics()-1);
//                character.setStealth(character.getStealth()-1);
//                character.setSleightOfHand(character.getSleightOfHand()-1);
//            }
//            case 10, 11 -> {
//                character.setAcrobatics(character.getAcrobatics());
//                character.setStealth(character.getStealth());
//                character.setSleightOfHand(character.getSleightOfHand());
//            }
//            case 12, 13 -> {
//                character.setAcrobatics(character.getAcrobatics()+1);
//                character.setStealth(character.getStealth()+1);
//                character.setSleightOfHand(character.getSleightOfHand()+1);
//            }
//            case 14, 15 -> {
//                character.setAcrobatics(character.getAcrobatics()+2);
//                character.setStealth(character.getStealth()+2);
//                character.setSleightOfHand(character.getSleightOfHand()+2);
//            }
//            case 16, 17 -> {
//                character.setAcrobatics(character.getAcrobatics()+3);
//                character.setStealth(character.getStealth()+3);
//                character.setSleightOfHand(character.getSleightOfHand()+3);
//            }
//            case 18 -> {
//                character.setAcrobatics(character.getAcrobatics()+4);
//                character.setStealth(character.getStealth()+4);
//                character.setSleightOfHand(character.getSleightOfHand()+4);
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + test);
//        }
//    }
//
//    public void calculateIntellectSubstats(Character character) {
//        int test = statsList.get(3);
//        switch (test) {
//            case 1 -> {
//                character.setArcana(character.getArcana()-5);
//                character.setHistory(character.getHistory()-5);
//                character.setInvestigation(character.getInvestigation()-5);
//                character.setNature(character.getNature()-5);
//                character.setReligion(character.getReligion()-5);
//            }
//            case 2, 3 -> {
//                character.setArcana(character.getArcana()-4);
//                character.setHistory(character.getHistory()-4);
//                character.setInvestigation(character.getInvestigation()-4);
//                character.setNature(character.getNature()-4);
//                character.setReligion(character.getReligion()-4);
//            }
//            case 4, 5 -> {
//                character.setArcana(character.getArcana()-3);
//                character.setHistory(character.getHistory()-3);
//                character.setInvestigation(character.getInvestigation()-3);
//                character.setNature(character.getNature()-3);
//                character.setReligion(character.getReligion()-3);
//            }
//            case 6, 7 -> {
//                character.setArcana(character.getArcana()-2);
//                character.setHistory(character.getHistory()-2);
//                character.setInvestigation(character.getInvestigation()-2);
//                character.setNature(character.getNature()-2);
//                character.setReligion(character.getReligion()-2);
//            }
//            case 8, 9 -> {
//                character.setArcana(character.getArcana()-1);
//                character.setHistory(character.getHistory()-1);
//                character.setInvestigation(character.getInvestigation()-1);
//                character.setNature(character.getNature()-1);
//                character.setReligion(character.getReligion()-1);
//            }
//            case 10, 11 -> {
//                character.setArcana(character.getArcana());
//                character.setHistory(character.getHistory());
//                character.setInvestigation(character.getInvestigation());
//                character.setNature(character.getNature());
//                character.setReligion(character.getReligion());
//            }
//            case 12, 13 -> {
//                character.setArcana(character.getArcana()+1);
//                character.setHistory(character.getHistory()+1);
//                character.setInvestigation(character.getInvestigation()+1);
//                character.setNature(character.getNature()+1);
//                character.setReligion(character.getReligion()+1);
//            }
//            case 14, 15 -> {
//                character.setArcana(character.getArcana()+2);
//                character.setHistory(character.getHistory()+2);
//                character.setInvestigation(character.getInvestigation()+2);
//                character.setNature(character.getNature()+2);
//                character.setReligion(character.getReligion()+2);
//            }
//            case 16, 17 -> {
//                character.setArcana(character.getArcana()+3);
//                character.setHistory(character.getHistory()+3);
//                character.setInvestigation(character.getInvestigation()+3);
//                character.setNature(character.getNature()+3);
//                character.setReligion(character.getReligion()+3);
//            }
//            case 18 -> {
//                character.setArcana(character.getArcana()+4);
//                character.setHistory(character.getHistory()+4);
//                character.setInvestigation(character.getInvestigation()+4);
//                character.setNature(character.getNature()+4);
//                character.setReligion(character.getReligion()+4);
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + test);
//        }
//    }
//    public void calculateWisdomSubstats(Character character) {
//        int test = statsList.get(4);
//
//        switch (test) {
//            case 1 -> {
//                character.setAnimalHandling(character.getAnimalHandling()-5);
//                character.setInsight(character.getInsight()-5);
//                character.setMedicine(character.getMedicine()-5);
//                character.setPerception(character.getPerception()-5);
//                character.setSurvival(character.getSurvival()-5);
//            }
//            case 2, 3 -> {
//                character.setAnimalHandling(character.getAnimalHandling()-4);
//                character.setInsight(character.getInsight()-4);
//                character.setMedicine(character.getMedicine()-4);
//                character.setPerception(character.getPerception()-4);
//                character.setSurvival(character.getSurvival()-4);
//            }
//            case 4, 5 -> {
//                character.setAnimalHandling(character.getAnimalHandling()-3);
//                character.setInsight(character.getInsight()-3);
//                character.setMedicine(character.getMedicine()-3);
//                character.setPerception(character.getPerception()-3);
//                character.setSurvival(character.getSurvival()-3);
//            }
//            case 6, 7 -> {
//                character.setAnimalHandling(character.getAnimalHandling()-2);
//                character.setInsight(character.getInsight()-2);
//                character.setMedicine(character.getMedicine()-2);
//                character.setPerception(character.getPerception()-2);
//                character.setSurvival(character.getSurvival()-2);
//            }
//            case 8, 9 -> {
//                character.setAnimalHandling(character.getAnimalHandling()-1);
//                character.setInsight(character.getInsight()-1);
//                character.setMedicine(character.getMedicine()-1);
//                character.setPerception(character.getPerception()-1);
//                character.setSurvival(character.getSurvival()-1);
//            }
//            case 10, 11 -> {
//                character.setAnimalHandling(character.getAnimalHandling());
//                character.setInsight(character.getInsight());
//                character.setMedicine(character.getMedicine());
//                character.setPerception(character.getPerception());
//                character.setSurvival(character.getSurvival());
//            }
//            case 12, 13 -> {
//                character.setAnimalHandling(character.getAnimalHandling()+1);
//                character.setInsight(character.getInsight()+1);
//                character.setMedicine(character.getMedicine()+1);
//                character.setPerception(character.getPerception()+1);
//                character.setSurvival(character.getSurvival()+1);
//            }
//            case 14, 15 -> {
//                character.setAnimalHandling(character.getAnimalHandling()+2);
//                character.setInsight(character.getInsight()+2);
//                character.setMedicine(character.getMedicine()+2);
//                character.setPerception(character.getPerception()+2);
//                character.setSurvival(character.getSurvival()+2);
//            }
//            case 16, 17 -> {
//                character.setAnimalHandling(character.getAnimalHandling()+3);
//                character.setInsight(character.getInsight()+3);
//                character.setMedicine(character.getMedicine()+3);
//                character.setPerception(character.getPerception()+3);
//                character.setSurvival(character.getSurvival()+3);
//            }
//            case 18 -> {
//                character.setAnimalHandling(character.getAnimalHandling()+4);
//                character.setInsight(character.getInsight()+4);
//                character.setMedicine(character.getMedicine()+4);
//                character.setPerception(character.getPerception()+4);
//                character.setSurvival(character.getSurvival()+4);
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + test);
//        }
//    }
//    public void calculateCharismaSubstats(Character character) {
//        int test = statsList.get(5);
//        switch (test) {
//            case 1 -> {
//                character.setDeception(character.getDeception()-5);
//                character.setIntimidation(character.getIntimidation()-5);
//                character.setPerformance(character.getPerformance()-5);
//                character.setPersuasion(character.getPersuasion()-5);
//            }
//            case 2, 3 -> {
//                character.setDeception(character.getDeception()-4);
//                character.setIntimidation(character.getIntimidation()-4);
//                character.setPerformance(character.getPerformance()-4);
//                character.setPersuasion(character.getPersuasion()-4);
//            }
//            case 4, 5 -> {
//                character.setDeception(character.getDeception()-3);
//                character.setIntimidation(character.getIntimidation()-3);
//                character.setPerformance(character.getPerformance()-3);
//                character.setPersuasion(character.getPersuasion()-3);
//            }
//            case 6, 7 -> {
//                character.setDeception(character.getDeception()-2);
//                character.setIntimidation(character.getIntimidation()-2);
//                character.setPerformance(character.getPerformance()-2);
//                character.setPersuasion(character.getPersuasion()-2);
//            }
//            case 8, 9 -> {
//                character.setDeception(character.getDeception()-1);
//                character.setIntimidation(character.getIntimidation()-1);
//                character.setPerformance(character.getPerformance()-1);
//                character.setPersuasion(character.getPersuasion()-1);
//            }
//            case 10, 11 -> {
//                character.setDeception(character.getDeception());
//                character.setIntimidation(character.getIntimidation());
//                character.setPerformance(character.getPerformance());
//                character.setPersuasion(character.getPersuasion());
//            }
//            case 12, 13 -> {
//                character.setDeception(character.getDeception()+1);
//                character.setIntimidation(character.getIntimidation()+1);
//                character.setPerformance(character.getPerformance()+1);
//                character.setPersuasion(character.getPersuasion()+1);
//            }
//            case 14, 15 -> {
//                character.setDeception(character.getDeception()+2);
//                character.setIntimidation(character.getIntimidation()+2);
//                character.setPerformance(character.getPerformance()+2);
//                character.setPersuasion(character.getPersuasion()+2);
//            }
//            case 16, 17 -> {
//                character.setDeception(character.getDeception()+3);
//                character.setIntimidation(character.getIntimidation()+3);
//                character.setPerformance(character.getPerformance()+3);
//                character.setPersuasion(character.getPersuasion()+3);
//            }
//            case 18 -> {
//                character.setDeception(character.getDeception()+4);
//                character.setIntimidation(character.getIntimidation()+4);
//                character.setPerformance(character.getPerformance()+4);
//                character.setPersuasion(character.getPersuasion()+4);
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + test);
//        }
//    }
//
//    public boolean determineIfValidRace(Race race, SubRace subRace) {
//        for (Race index: Race.values()) {
//            if (index == race) {
//                return true;
//            }
//        }
//        for (SubRace index: SubRace.values()) {
//            if (index == subRace) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void calculateSpeed(Character character) {
//        switch(character.getRace()) {
//            case Elf, Tiefling, Orc, Dragonborn, Human, Halfelf, Halforc -> character.setSpeed(30);
//            case Gnome, Halfling, Dwarf -> character.setSpeed(25);
//            default -> throw new IllegalStateException("Unexpected value: " + character.getRace());
//        }
//        switch(character.getSubrace()) {
//            case EladrinElf, HighElf, WoodElf -> character.setSpeed(30);
//            case DeepGnome, ForestGnome, RockGnome, GrayDwarf,
//                    HillDwarf, MountainDwarf, LightFootHalfling, StoutHalfling -> character.setSpeed(25);
//            default -> throw new IllegalStateException("Unexpected value: " + character.getSubrace());
//        }
//    }
//}
