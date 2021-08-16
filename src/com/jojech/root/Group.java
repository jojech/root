package com.jojech.root;

public class Group {
    private int noPlayers;
    private Player[] players;

    public Group(int noPlayers) {
        this.noPlayers = noPlayers;
    }

    public Group(int noPlayers, Player[] players) {
        this.noPlayers = noPlayers;
        this.players = players;
    }

    public int getNoPlayers() {
        return noPlayers;
    }

    public void setNoPlayers(int noPlayers) {
        this.noPlayers = noPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
