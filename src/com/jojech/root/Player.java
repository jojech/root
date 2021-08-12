package com.jojech.root;

public class Player {
    private String playerName;
    private Faction playing = null;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Faction getPlaying() {
        return playing;
    }

    public void setPlaying(Faction playing) {
        this.playing = playing;
    }
}
