import heros.Personnage;

import java.util.*;

public class Plateau {

    private static Cases[] tableau = {Cases.DRAGON, Cases.DRAGON, Cases.DRAGON, Cases.DRAGON, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.MASSUE, Cases.MASSUE, Cases.MASSUE, Cases.MASSUE, Cases.MASSUE, Cases.EPEE, Cases.EPEE, Cases.EPEE, Cases.EPEE, Cases.ECLAIR, Cases.ECLAIR, Cases.ECLAIR, Cases.ECLAIR, Cases.ECLAIR, Cases.BOULE_FEU, Cases.BOULE_FEU, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.GRANDE_POTION, Cases.GRANDE_POTION, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE,};
    private int position = 0;

    enum Cases {
        VIDE, MASSUE, EPEE, ECLAIR, BOULE_FEU, PETITE_POTION, GRANDE_POTION, GOBELIN, SORCIER, DRAGON
    }


    //Constructor
    public Plateau(Bdd bdd, Personnage player) {
        List<Cases> list = Arrays.asList(getTableau());
        Collections.shuffle(list);
        setTableau(list.toArray(tableau));
        System.out.println(Arrays.toString(tableau));
//        bdd.ajouterTableau(getTableau(), player);
    }


    //Methodes

    /**
     * Méthode qui permet d'alimenter une variable qui servira a la navigation dans le tableau jeu
     */
    public void avancementPlateau(Personnage player) {
        String debutLancement;
//        String choixDuHeroe;
        while (getPosition() < 63) {
            System.out.print("Taper la lettre l pour lancer le dé (stop pour quitter le jeu) :");
            debutLancement = Menu.choixClavier("");
            try {
            if (debutLancement.equals("l")) {

                    int result = this.getPosition() + Menu.lancerD();
//                if (result >= 63) {
//                   setPosition(63);
//                } else {
                    setPosition(result);
//                }
                    System.out.println("__________Votre position est maintenant sur la case : " + (position + 1));

                    if (tableau[getPosition()] == Cases.DRAGON) {
                        imgDragon();
                        veuxTuCombattre();
                    }
                    if (tableau[getPosition()] == Cases.SORCIER) {
                        imgSorcier();
                        veuxTuCombattre();
                    }
                    if (tableau[getPosition()] == Cases.GOBELIN) {
                        imgGobelin();
                        veuxTuCombattre();
                    }
                    realisationDeLevenement(getPosition(), player);
                    tableau[getPosition()] = Cases.VIDE;
                    System.out.println("\nRappel de tes parametres :");
                    System.out.println(player);

                } else if (debutLancement.equals("stop")) {
                    System.exit(0);
                }

            }catch(Exception e) {
                setPosition(63);
            }

        }
    }

    public void realisationDeLevenement(int position, Personnage player) throws Exception {
        if (tableau[position] == Cases.VIDE) {
            System.out.println("\n____________________Tu es sur une case vide");
        } else if (tableau[position] == Cases.PETITE_POTION) {
            System.out.println("\n____________________Tu es sur une case petite potion");
            CasePotion p = new CasePotion();
            p.casePotion(player, Cases.PETITE_POTION);
        } else if (tableau[position] == Cases.GRANDE_POTION) {
            System.out.println("\n____________________Tu es sur une case grande potion");
            CasePotion p = new CasePotion();
            p.casePotion(player, Cases.GRANDE_POTION);
        } else if (tableau[position] == Cases.MASSUE) {
            System.out.println("\n____________________Tu es sur une case massue");
            CaseEquipement e = new CaseEquipement();
            e.caseEquipement(player, Cases.MASSUE);
        } else if (tableau[position] == Cases.EPEE) {
            System.out.println("\n____________________Tu es sur une case epee");
            CaseEquipement e = new CaseEquipement();
            e.caseEquipement(player, Cases.EPEE);
        } else if (tableau[position] == Cases.ECLAIR) {
            System.out.println("\n____________________Tu es sur une case eclair");
            CaseEquipement e = new CaseEquipement();
            e.caseEquipement(player, Cases.ECLAIR);
        } else if (tableau[position] == Cases.BOULE_FEU) {
            System.out.println("\n____________________Tu es sur une case boule de feu");
            CaseEquipement e = new CaseEquipement();
            e.caseEquipement(player, Cases.BOULE_FEU);
        } else if (tableau[position] == Cases.GOBELIN) {
            CaseEnnemi c = new CaseEnnemi(player, Cases.GOBELIN, this);
        } else if (tableau[position] == Cases.SORCIER) {
            CaseEnnemi c = new CaseEnnemi(player, Cases.SORCIER, this);
        } else if (tableau[position] == Cases.DRAGON) {
            CaseEnnemi c = new CaseEnnemi(player, Cases.DRAGON, this);
        } else{
            throw new Exception("error");
        }
    }

    public void veuxTuCombattre() {
        String choixDuHeroe;
        choixDuHeroe = Menu.choixClavier("Veux-tu combattre ? (o pour oui n pour non)");
        while (!choixDuHeroe.equals("n") && (!choixDuHeroe.equals("o"))) {
            choixDuHeroe = Menu.choixClavier("Veux-tu combattre ? (o pour oui n pour non)");
        }
        if (choixDuHeroe.equals("n")) {
            int nombreCaseReculer = (int) (Math.random() * 6 + 1);
            setPosition(getPosition() - nombreCaseReculer);
            if (getPosition() < 0) {
                setPosition(1);
            }
            System.out.println("__________Vous avez reculé sur la case : " + (position + 1));
        }
    }

    public void imgGobelin() {
        System.out.println("____________________C'est un Gobelin              " +
                "               ,      ,\n" +
                "            /(.-\"\"-.)\\\n" +
                "        |\\  \\/      \\/  /|\n" +
                "        | \\ / =.  .= \\ / |\n" +
                "        \\( \\   o\\/o   / )/\n" +
                "         \\_, '-/  \\-' ,_/\n" +
                "           /   \\__/   \\\n" +
                "           \\ \\__/\\__/ /\n" +
                "         ___\\ \\|--|/ /___\n" +
                "       /`    \\      /    `\\\n" +
                "      /       '----'       \\");
    }

    public void imgSorcier() {
        System.out.println("____________________C'est un Sorcier                                        " +
                "                                     .\n" +
                "                             /^\\     .\n" +
                "                        /\\   \"V\"\n" +
                "                       /__\\   I      O  o\n" +
                "                      //..\\\\  I     .\n" +
                "                      \\].`[/  I\n" +
                "                      /l\\/j\\  (]    .  O\n" +
                "                     /. ~~ ,\\/I          .\n" +
                "                     \\\\L__j^\\/I       o\n" +
                "                      \\/--v}  I     o   .\n" +
                "                      |    |  I   _________\n" +
                "                      |    |  I c(`       ')o\n" +
                "                      |    l  I   \\.     ,/          \n" +
                "                    _/j  L l\\_!  _//^---^\\\\_\n" +
                "                 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void imgDragon() {
        System.out.println("____________________C'est un Dragon  " +
                "       ,,\n" +
                "`\"\"*$b..\n" +
                "     \"\"*$o.\n" +
                "         \"$$o.\n" +
                "           \"*$$o.\n" +
                "              \"$$$o.\n" +
                "                \"$$$$bo...       ..o:\n" +
                "                  \"$$$$$$$$booocS$$$    ..    ,.\n" +
                "               \".    \"*$$$$SP     V$o..o$$. .$$$b\n" +
                "                \"$$o. .$$$$$o. ...A$$$$$$$$$$$$$$b\n" +
                "          \"\"bo.   \"*$$$$$$$$$$$$$$$$$$$$P*$$$$$$$$:\n" +
                "             \"$$.    V$$$$$$$$$P\"**\"\"*\"'   VP  * \"l\n" +
                "               \"$$$o.4$$$$$$$$X\n" +
                "                \"*$$$$$$$$$$$$$AoA$o..oooooo..           .b\n" +
                "                       .X$$$$$$$$$$$P\"\"     \"\"*oo,,     ,$P\n" +
                "                      $$P\"\"V$$$$$$$:    .        \"\"*****\"\n" +
                "                    .*\"    A$$$$$$$$o.4;      .\n" +
                "                         .oP\"\"   \"$$$$$$b.  .$;\n" +
                "                                  A$$$$$$$$$$P\n" +
                "                                  \"  \"$$$$$P\"\n" +
                "                                      $$P*\"\n" +
                "                                     .$\"\n" +
                "                                     \"");
    }




    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Cases[] getTableau() {
        return tableau;
    }

    public void setTableau(Cases[] tableau) {
        this.tableau = tableau;
    }


}
