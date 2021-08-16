package com.jojech.root;

/*
Idea: a campaign version of root where the winner of a game gets perks
Possible consequences of winning:
 - Must select a Red Faction?
 - Get a leader token?
 - Start with a hireling at the beginning of the game
*/
public class Game {
    private Configuration expansions;
    private Group players;

    public Game(Configuration expansions, int noPlayers, Player[] pList) {
        this.expansions = expansions;
        this.players = new Group(noPlayers,pList);
    }
}

/*
Root balances
Eyrie: build roosts even in clearings with a roost already
Marquise: (Workshop Marquise)
Woodland Alliance: (the adjustments where they're more aggressive and get less points from sympathy)
Vagabond: despot infamy, questing counts as aiding the ruler of the clearing, allied means you get points for initiating battles

Make sure to use the ADSet rules as well
*/