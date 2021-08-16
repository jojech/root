package com.jojech.root;

public class Configuration {
    private boolean riverfolk;
    private boolean underworld;
    private boolean marauders;
    private boolean vagabond;

    public Configuration() {
        this.riverfolk = false;
        this.underworld = false;
        this.marauders = false;
        this.vagabond = false;

    }

    public Configuration(boolean riverfolk, boolean underworld, boolean marauders, boolean vagabond) {
        this.riverfolk = riverfolk;
        this.underworld = underworld;
        this.marauders = marauders;
        this.vagabond = vagabond;
    }

    public boolean isRiverfolk() {
        return riverfolk;
    }

    public void setRiverfolk(boolean riverfolk) {
        this.riverfolk = riverfolk;
    }

    public boolean isUnderworld() {
        return underworld;
    }

    public void setUnderworld(boolean underworld) {
        this.underworld = underworld;
    }

    public boolean isMarauders() {
        return marauders;
    }

    public void setMarauders(boolean marauders) {
        this.marauders = marauders;
    }

    public boolean isVagabond() {
        return vagabond;
    }

    public void setVagabond(boolean vagabond) {
        this.vagabond = vagabond;
    }
}
