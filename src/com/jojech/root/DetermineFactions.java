package com.jojech.root;



import java.util.ArrayList;
import java.util.Scanner;

import static com.jojech.root.FactionsGenerator.trimByExpansion;

/*
Class: DetermineFactions
Purpose: this program facilitates automated ADSet faction drafting and selection for tournament play.
User Inputs: # of Players, Expansions on hand, and (eventually) each player's faction selection.
Outputs: Viable faction setups, and available options for player selection.
 */
public class DetermineFactions {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Get number of players from user
        System.out.print("How many players are playing? (including bots): ");
        int input = scan.nextInt();
        while (input < 2 || input > 9) {
            System.out.println("Please enter a valid number of players: ");
            input = scan.nextInt();
        }

        // Generate Faction List
        Faction[] fList = FactionsGenerator.createFactions();
        boolean riverfolk = false;
        boolean underworld = false;
        boolean marauders = false;
        char cInput;

        System.out.println("Do you have the Riverfolk Expansion? (y/n): ");
        cInput = scan.next().trim().toLowerCase().charAt(0);
        if (cInput == 'y') riverfolk = true;

        System.out.println("Do you have the Underworld Expansion? (y/n): ");
        cInput = scan.next().trim().toLowerCase().charAt(0);
        if (cInput == 'y') underworld = true;

        System.out.println("Do you have the Marauders Expansion? (y/n): ");
        cInput = scan.next().trim().toLowerCase().charAt(0);
        if (cInput == 'y') marauders = true;

        Faction[] list = trimByExpansion(fList,riverfolk,underworld,marauders);

        // Create Player List
        Player[] pList = createPlayerList(true, input);
        // for (Player b : pList) System.out.println(b.getPlayerName());


        // Follow ADSet Rules
        if (input > list.length) System.out.print("Not enough factions...");
        else {
            // ADSetup
            int noPlayers = input;
            System.out.print("ADS Pool: ");
            ArrayList<Faction> factionDraft = new ArrayList<>();
            Faction[] ads = createADSetPool(input,list);
            for (Faction z : ads) {
                // System.out.print("\n"+z.getFactionName()+" - "+z.getReach());
                factionDraft.add(z);
            }

            for (int i = 0; i < noPlayers; i++) {
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

    public static Faction randomFaction(Faction[] List) {
        return List[(int)Math.floor(Math.random()*List.length)];
    }

    public static Faction randomFaction(ArrayList<Faction> List) {
        return List.get((int)Math.floor(Math.random()*List.size()));
    }

    public static int randomIndex(int length) {
        return (int)Math.floor(Math.random()*length);
    }

    public static int randomRedIndex(Faction[] List) {
        int rint;
        do rint = randomIndex(List.length); while (!List[rint].isRed());
        return rint;
    }

    public static Faction randomRedFaction(Faction[] List) {
        Faction result = new Faction(false,0,"Null","null",0);
        if (!result.isRed()) result = randomFaction(List);
        return result;
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

    public static int reachFinder(int noPlayers) {
        int reach;
        switch (noPlayers) {
            case 2 -> reach = 17;
            case 3 -> reach = 18;
            case 4 -> reach = 21;
            case 5 -> reach = 25;
            case 6 -> reach = 28;
            default -> reach = 30;
        }
        return reach;
    }
}
