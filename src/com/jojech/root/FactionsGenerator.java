package com.jojech.root;

import com.jojech.root.Faction;

public class FactionsGenerator {

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
