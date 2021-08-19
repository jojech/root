package com.jojech.root;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    /*
    Method: main()
    Purpose: Initiates setup for a game of Root. Most notably, randomizes faction options to create variable game setups.
     */
    public static void main(String[] args) {
        char cInput = 's';
        Scanner scan = new Scanner(System.in);
        // Print Welcome Message
        System.out.println("Welcome to Root Tea: a program that helps make playing Root easier!" +
                "\n\nFirst things first...");

        // Initiate Configuration
        Configuration expansions = updateExpansions();
        // Initiate Group
        Group players = updatePlayers();
        // Initiate Game
        Game settings;
        do {
            // Put interface within these brackets
            // Initiate interface
            System.out.println("\n============== Menu ==============" +
                    "\nSelect an option from the list below:" +
                    "\nDraft factions using ADSet rules (d)" +
                    "\nChange Expansion settings (e)" +
                    "\nChange Player List (p)" +
                    "\nExit (x)");
            cInput = scan.next().trim().toLowerCase().charAt(0);
            switch (cInput) {
                case 'd' -> settings = adSetDraftFactions(expansions,players);
                case 'e' -> expansions = updateExpansions();
                case 'p' -> players = updatePlayers();
                case 'x' -> System.out.print("Thank you for playing! ");
                default -> System.out.println("Invalid input...");
            }
        } while (cInput != 'x');
        System.out.println("Exiting the program...");
    }

    // This method handles the drafting of factions for players to set up a game
    public static Game adSetDraftFactions(Configuration expansions,Group players) {
        // Initiate ADSET Drafting of factions.
        Faction[] list = trimByExpansion(createFactions()
                ,expansions.isRiverfolk()
                ,expansions.isUnderworld()
                ,expansions.isMarauders());
        Player[] pList = players.getPlayers();
        if (players.getNoPlayers() > list.length) System.out.print("Not enough factions...");
        else {
            // System.out.print("ADS Pool: ");
            ArrayList<Faction> factionDraft = new ArrayList<>();
            Faction[] ads = createADSetPool(players.getNoPlayers(), list, players.getNoPlayers() == list.length);
            for (Faction z : ads) {
                // System.out.print("\n"+z.getFactionName()+" - "+z.getReach());
                factionDraft.add(z);
            }
            Scanner scan = new Scanner(System.in);
            int input;
            for (int i = 0; i < players.getNoPlayers(); i++) {
                for (int j = 0; j < factionDraft.size(); j++) {
                    System.out.print("\n("+j+") "+factionDraft.get(j).getFactionName());
                }
                System.out.print("\n"+pList[i].getPlayerName()+", pick a faction from the above list by entering the number in parentheses: ");
                input = scan.nextInt();
                pList[i].setPlaying(factionDraft.get(input));
                factionDraft.remove(input);
                System.out.print("\nCongratulations, you successfully selected the "+pList[i].getPlaying().getNickname()
                        +"\nSetup your faction now.\n\n");
            }

            System.out.print("\nIn Summary, the turn order is as follows: ");
            for (int i = pList.length-1; i >= 0; i--) {
                System.out.print("\n"+pList[i].getPlayerName()+" as the "+pList[i].getPlaying().getNickname());
            }
        }
        return new Game(expansions,players.getNoPlayers(),pList);
    }

    // Method returns a random number within an array length.
    // Purpose: to pick a random faction within the faction list.
    public static int randomIndex(int length) {
        return (int)Math.floor(Math.random()*length);
    }

    // Method returns the index of a red faction within a list of factions
    public static int randomRedIndex(Faction[] List) {
        int rint;
        do rint = randomIndex(List.length); while (!List[rint].isRed());
        return rint;
    }

    // Method returns a list of random factions based on the player count
    // Purpose: to create a unique game experience every time players sit at the table.
    public static Faction[] createADSetPool(int noPlayers, Faction[] List, boolean fulltable) {
        if (!fulltable) noPlayers += 1;
        Faction[] result = new Faction[noPlayers];
        ArrayList<Faction> list = new ArrayList<>();
        for (Faction x : List) list.add(x);
        int random = randomRedIndex(List);
        result[0] = list.get(random);
        list.remove(random);
        for (int i = 1; i < result.length; i++) {
            random = randomIndex(list.size());
            result[i] = list.get(random);
            list.remove(random);
        }
        return result;
    }

    // Method gathers user input on which expansions the group is playing with
    // Purpose: to filter which factions are available to play with.
    public static Configuration updateExpansions() {
        // Determine expansion(s) owned by group
        Scanner scan = new Scanner(System.in);
        Configuration settings = new Configuration();

        char cInput;

        System.out.println("Do you have the Riverfolk Expansion? (y/n): ");
        cInput = scan.next().trim().toLowerCase().charAt(0);
        if (cInput == 'y') settings.setRiverfolk(true);

        System.out.println("Do you have the Underworld Expansion? (y/n): ");
        cInput = scan.next().trim().toLowerCase().charAt(0);
        if (cInput == 'y') settings.setUnderworld(true);

        System.out.println("Do you have the Marauders Expansion? (y/n): ");
        cInput = scan.next().trim().toLowerCase().charAt(0);
        if (cInput == 'y') settings.setMarauders(true);

        System.out.println("Do you have the Vagabond Expansion? (y/n): ");
        cInput = scan.next().trim().toLowerCase().charAt(0);
        if (cInput == 'y') settings.setVagabond(true);

        System.out.println("Expansion settings updated!");
        return settings;
    }

    // Method gathers user input to determine number of players and their names
    // Purpose: to help in the drafting of factions.
    public static Group updatePlayers() {
        Scanner scan = new Scanner(System.in);
        int noPlayers;
        char cInput;
        System.out.println("How many players are playing?: ");
        noPlayers = scan.nextInt();
        while (noPlayers < 2 || noPlayers > 9) {
            System.out.println("Please enter a valid number of players: ");
            noPlayers = scan.nextInt();
        }

        System.out.println("Would you like to name players?: (y/n) ");
        cInput = scan.next().trim().toLowerCase().charAt(0);
        if (cInput == 'y') {
            Group players = new Group(noPlayers,createPlayerList(false,noPlayers));
            return players;
        }
        Group players = new Group(noPlayers,createPlayerList(true,noPlayers));
        System.out.println("Player settings updated!");
        return players;
    }

    // Determines how to name players and returns a list of user-entered names
    public static Player[] createPlayerList(boolean auto, int input) {
        // Create Players
        Player[] pList = new Player[input];
        int no = 1;
        // Auto-name system
        if (auto) {
            for (int i = 0; i < input; i++) {
                pList[i] = new Player("Player "+ (i + 1));
            }
        } else {
            // User input Name system
            Scanner scan = new Scanner(System.in);
            String username;
            for (int i = 0; i < input; i++) {
                System.out.print("\nPlayer "+no+", enter your name: ");
                username = scan.next();
                pList[i] = new Player(username);
                no++;
            }
        }
        return pList;
    }

    // Generate factions
    public static Faction[] createFactions() {
        int noFactions = 9;
        Faction[] result = new Faction[]{
                new Faction(true,10,"Marquise de Cat","cats",0)
                , new Faction(true,7,"Eyrie Dynasties","birds",0)
                , new Faction(true,8,"Underground Duchy","moles",2)
                , new Faction(false,2,"Lizard Cult","lizards",1)
                , new Faction(false,3,"Corvid Conspiracy","crows",2)
                , new Faction(false,5,"Riverfolk Company","otters",1)
                , new Faction(false,3,"Woodland Alliance","alliance",0)
                , new Faction(false,5,"Vagabond","vagabond",0)
                , new Faction(false,5,"Vagabond","vagabond",1)
                , new Faction(true,8,"Lord of the Hundreds","rats",3)
                , new Faction(true,8,"Keepers in Iron","badgers",3)
        };
        return result;
    }

    // Trim Factions
    public static Faction[] trimByExpansion(Faction[] fList, boolean rf, boolean uw, boolean ma) {
        int total = 4;
        if (rf) total += 3;
        if (uw) total += 2;
        if (ma) total += 2;
        Faction[] result = new Faction[total];
        int rc = 0;
        for (int i = 0; i < fList.length; i++) {
            switch (fList[i].getBox()) {
                case 0:
                    result[rc] = fList[i];
                    rc++;
                    break;
                case 1:
                    if (rf) {
                        result[rc] = fList[i];
                        rc++;
                    }
                    break;
                case 2:
                    if (uw) {
                        result[rc] = fList[i];
                        rc++;
                    }
                    break;
                case 3:
                    if (ma) {
                        result[rc] = fList[i];
                        rc++;
                    }
                    break;
            }
        }
        return result;
    }
}
