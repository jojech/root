package com.jojech.root;

import java.util.Objects;

public class Faction {
    private boolean red;
    private int reach;
    private String factionName;
    private String nickname;
    // box is asking which set it came in
    // 0 = base
    // 1 = Riverfolk
    // 2 = Underworld
    // 3 = Marauders
    private int box;
    private boolean vb;


    public Faction(boolean red, int reach, String factionName, String nickname, int box) {
        this.red = red;
        this.reach = reach;
        this.factionName = factionName;
        this.nickname = nickname;
        this.box = box;
        this.vb = false;
    }

    public Faction(boolean red, int reach, String factionName, String nickname, int box, boolean vb) {
        this.red = red;
        this.reach = reach;
        this.factionName = factionName;
        this.nickname = nickname;
        this.box = box;
        this.vb = vb;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }

    public int getReach() {
        return reach;
    }

    public void setReach(int reach) {
        this.reach = reach;
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public boolean isVb() {
        return vb;
    }

    public void setVb(boolean vb) {
        this.vb = vb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faction faction = (Faction) o;
        return red == faction.red && reach == faction.reach && box == faction.box && Objects.equals(factionName, faction.factionName) && Objects.equals(nickname, faction.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, reach, factionName, nickname, box);
    }
}
