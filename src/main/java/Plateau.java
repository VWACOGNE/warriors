import heros.Personnage;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Plateau {

    private String tableauDuJoueurSauve = null;
    private String tableauASauve = null;
    private static Cases[] tableau = {Cases.DRAGON, Cases.DRAGON, Cases.DRAGON, Cases.DRAGON, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.SORCIER, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.GOBELIN, Cases.MASSUE, Cases.MASSUE, Cases.MASSUE, Cases.MASSUE, Cases.MASSUE, Cases.EPEE, Cases.EPEE, Cases.EPEE, Cases.EPEE, Cases.ECLAIR, Cases.ECLAIR, Cases.ECLAIR, Cases.ECLAIR, Cases.ECLAIR, Cases.BOULE_FEU, Cases.BOULE_FEU, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.PETITE_POTION, Cases.GRANDE_POTION, Cases.GRANDE_POTION, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE, Cases.VIDE,};
    private int position = 0;

    enum Cases {
        VIDE, MASSUE, EPEE, ECLAIR, BOULE_FEU, PETITE_POTION, GRANDE_POTION, GOBELIN, SORCIER, DRAGON
    }



    /**
     * Assigne aux paramettres les valeurs
     * @param
     * @param player
     * @param id
     */
    //Constructor
    public Plateau(BddPlateau bddPlateau,BddPersonnage bddPersonnage,BddPosition bddPosition, Personnage player, Integer id) {
        if (id == null) {
            List<Cases> list = Arrays.asList(getTableau());
            Collections.shuffle(list);
            setTableau(list.toArray(tableau));
            tableauASauve = Arrays.toString(tableau);
            bddPersonnage.ajouterUtilisateur(player, this);
        } else {
            tableauDuJoueurSauve = bddPlateau.recupererTableau(id);
            tableauDuJoueurSauve = tableauDuJoueurSauve.replace("[", "");
            tableauDuJoueurSauve = tableauDuJoueurSauve.replace("]", "");
            List<String> strings = Arrays.asList(tableauDuJoueurSauve.split(", "));
            position = bddPosition.recupererPosition(id);
            System.out.println(strings);
            System.out.println("Tu reprends sur la position : " + getPosition());
            for (int i = 0; i < strings.size(); i++) {
                tableau[i] = Cases.valueOf(strings.get(i));
            }
        }
    }

    public Plateau() {

    }


    //Methodes

    /**
     * Méthode qui permet d'alimenter une variable qui servira a la navigation dans le tableau jeu
     */
    public void avancementPlateau(Personnage player, BddPersonnage bddPersonnage, Des de) throws Exception {
        String debutLancement;
        while (getPosition() < 63) {
            debutLancement = Menu.choixClavier("Taper la lettre l pour lancer le dé (stop pour quitter le jeu) :");

                if (debutLancement.equals("l")) {
                    int result = this.getPosition() + de.lancerD();

                    try {
                    setPosition(result);
                    }catch (DepassementPlateauException e) {
                        //System.out.println("je suis dans le catch");
                        break;
                    }

                    System.out.println("__________Votre position est maintenant sur la case : " + (position + 1));
                    realisationDeLevenement(getPosition(), player);
                    tableau[getPosition()] = Cases.VIDE;
                    System.out.println("\nRappel de tes parametres :");
                    System.out.println(player);

                } else if (debutLancement.equals("stop")) {
                    String veuxTuSauvegarder = Menu.choixClavier("Veux-tu sauvegarder ta partie ? (tape: o pour oui, n pour non)");
                    while (!veuxTuSauvegarder.equals("o") && (!veuxTuSauvegarder.equals("n"))) {
                        veuxTuSauvegarder = Menu.choixClavier("Veux-tu sauvegarder ta partie ? (tape: o pour oui, n pour non)");
                    }
                    if (veuxTuSauvegarder.equals("o")) {
                        bddPersonnage.majUtilisateur(player, this, player.getNomGuerrier());
                        System.exit(0);
                    }

                }
        }
    }

    /**
     * Fonction pour réaliser l'évennement
     * @param position
     * @param player
     * @throws Exception
     */
    public void realisationDeLevenement(int position, Personnage player) throws Exception {
        if (tableau[position] == Cases.VIDE) {
            System.out.println("\n____________________Tu es sur une case vide");
        } else if (tableau[position] == Cases.PETITE_POTION) {
            System.out.println("\n____________________Tu es sur une case petite potion");
            new CasePetitePotion().utiliser(player);
        } else if (tableau[position] == Cases.GRANDE_POTION) {
            System.out.println("\n____________________Tu es sur une case grande potion");
            new CaseGrandePotion().utiliser(player);
        } else if (tableau[position] == Cases.MASSUE) {
            System.out.println("\n____________________Tu es sur une case massue");
            new CaseMassue().utiliser(player);
        } else if (tableau[position] == Cases.EPEE) {
            System.out.println("\n____________________Tu es sur une case epee");
            new CaseEpee().utiliser(player);
        } else if (tableau[position] == Cases.ECLAIR) {
            System.out.println("\n____________________Tu es sur une case eclair");
            new CaseEclair().utiliser(player);
        } else if (tableau[position] == Cases.BOULE_FEU) {
            System.out.println("\n____________________Tu es sur une case boule de feu");
            new CaseBouleDeFeu().utiliser(player);
        } else if (tableau[position] == Cases.GOBELIN) {
            imgGobelin();
            veuxTuCombattre(player, "gobelin");
        } else if (tableau[position] == Cases.SORCIER) {
            imgSorcier();
            veuxTuCombattre(player, "sorcier");
        } else if (tableau[position] == Cases.DRAGON) {
            imgDragon();
            veuxTuCombattre(player, "dragon");
        }
    }



    /**
     * Fonction pour poser la question si l'on veut combattre ou non
     */
    public void veuxTuCombattre(Personnage player, String ennemi) throws Exception {
        String choixDuHeroe;
        choixDuHeroe = Menu.choixClavier("Veux-tu combattre ? (o pour oui n pour non)");
        while (!choixDuHeroe.equals("n") && (!choixDuHeroe.equals("o"))) {
            choixDuHeroe = Menu.choixClavier("Veux-tu combattre ? (o pour oui n pour non)");
        }
        if (choixDuHeroe.equals("n")) {
            int nombreCaseReculer = (int) (Math.random() * 6 + 1);
            setPosition(getPosition() - nombreCaseReculer);
            if (getPosition() < 0) {
                setPosition(0);

            }
            System.out.println("__________Vous avez reculé sur la case : " + (position + 1));
            realisationDeLevenement(getPosition(),player);
        }else {
            if (ennemi.equals("gobelin")){
                new Gobelin().faireLeCombat(player, this);
            }
            if (ennemi.equals("sorcier")){
                new Sorcier().faireLeCombat(player, this);
            }
            if (ennemi.equals("dragon")){
                new Dragon().faireLeCombat(player, this);
            }
        }

    }

    /**
     * Img Gobelin
     */
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

    /**
     * img sorcier
     */
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

    /**
     * img dragon
     */
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

    public void setPosition(int position) throws DepassementPlateauException {
        this.position = position;
        if (this.position >= tableau.length){
            this.position = 63;
            throw new DepassementPlateauException();
        }
    }

    public Cases[] getTableau() {
        return tableau;
    }

    public void setTableau(Cases[] tableau) {
        this.tableau = tableau;
    }

    public String getTableauASauve() {
        return tableauASauve;
    }

    public void setTableauASauve(String tableauASauve) {
        this.tableauASauve = tableauASauve;
    }

    public String getTableauDuJoueurSauve() {
        return tableauDuJoueurSauve;
    }

    public void setTableauDuJoueurSauve(String tableauDuJoueurSauve) {
        this.tableauDuJoueurSauve = tableauDuJoueurSauve;
    }
}
