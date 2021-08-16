package com.jojech.root;

import java.util.ArrayList;
import java.util.Scanner;

import static com.jojech.root.FactionsGenerator.createFactions;
import static com.jojech.root.FactionsGenerator.trimByExpansion;

public class Main {
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
                default -> System.out.println("Invalid input...");
            }
        } while (cInput != 'x');
        System.out.println("Exiting the program...");
    }

    public static Game adSetDraftFactions(Configuration expansions,Group players) {
        // Initiate ADSET Drafting of factions.
        Faction[] list = trimByExpansion(createFactions()
                ,expansions.isRiverfolk()
                ,expansions.isUnderworld()
                ,expansions.isMarauders());
        Player[] pList = players.getPlayers();
        if (players.getNoPlayers() > list.length) System.out.print("Not enough factions...");
        else {
            System.out.print("ADS Pool: ");
            ArrayList<Faction> factionDraft = new ArrayList<>();
            Faction[] ads = createADSetPool(players.getNoPlayers(), list);
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

    public static int randomIndex(int length) {
        return (int)Math.floor(Math.random()*length);
    }

    public static int randomRedIndex(Faction[] List) {
        int rint;
        do rint = randomIndex(List.length); while (!List[rint].isRed());
        return rint;
    }

    public static Faction[] createADSetPool(int noPlayers, Faction[] List) {
        Faction[] result = new Faction[noPlayers + 1];
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
}
