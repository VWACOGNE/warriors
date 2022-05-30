import heros.Guerrier;
import heros.Magicien;
import heros.Personnage;

import java.util.List;
import java.util.Scanner;

public class Menu {


    public static void main(String[] args) throws Exception {
        Plateau P = null;
        Bdd bdd = new Bdd();
        List<Personnage> listPersoSauve = bdd.recupererUtilisateurs();
        Personnage player = null;
        String cheat = choixClavier("Veux-tu une partie avec dés truqués ? (o pour oui n pour non)");
        String sauvegarde = choixClavier("Veux-tu un personnage sauvegardé ? (o pour oui n pour non stop pour quitter)");
        while (!sauvegarde.equals("o") && (!sauvegarde.equals("n"))) {
            sauvegarde = choixClavier("Renseignes o pour oui ou n pour non ou stop pour quitter");
        }
        Des dice;
        if (cheat.equals("o")) {
            dice = new DesTruques();
        } else {
            dice = new DesNormaux();
        }
        if (sauvegarde.equals("o")) {
            for (int i = 0; i < listPersoSauve.size(); i++) {
                System.out.println("Personnage N°" + i);
                System.out.println(listPersoSauve.get(i));
            }
            int choixList = -1;
            Scanner clavier = new Scanner(System.in);
            while (choixList > (listPersoSauve.size()-1) || choixList <0) {
                try {
                    System.out.print("----------Quel personnage veux-tu ? ");
                    choixList = Integer.parseInt(clavier.next());
                } catch (NumberFormatException exception) {

                }
            }
            player = listPersoSauve.get(choixList);

            P = new Plateau(bdd, player, player.getIdHeros());
            P.avancementPlateau(player, bdd, dice);

        } else if (sauvegarde.equals("n")) {
            player = null;
            boolean nomChoisit = false;
            while (!nomChoisit) {
                player = createHeroe(choixPersonnage());
                if (listPersoSauve.isEmpty()) {
                    break;
                }
                for (int i = 0; i < listPersoSauve.size(); i++) {
                    if (player.getNomGuerrier().equalsIgnoreCase(listPersoSauve.get(i).getNomGuerrier())) {
                        System.out.print("____________________Votre nom existe déjà, changez le\n");
                        nomChoisit = false;
                        break;
                    } else if (!nomChoisit) {
                        nomChoisit = true;
                    }
                }
            }
            System.out.print("____________________Votre personnage est sauvegardé\n");
            P = new Plateau(bdd, player, null);
        }
        System.out.println(player);
        P.avancementPlateau(player, bdd, dice);
        bdd.supprimerUtilisateur(player);
        imgVictoire();
    }


    //-------------------------------METHODES----------------------------------

    /**
     * Fonction scanner clavier
     *
     * @param texte doit contenir le texte qui va s'afficher lors de la demande
     * @return retourne le resultat taper au clavier sous forme de String
     */
    static String choixClavier(String texte) {
        String result;
        Scanner clavier = new Scanner(System.in);
        System.out.print(texte);
        result = clavier.nextLine();
        return result;
    }

    /**
     * Fonction pour choisir entre heros.Guerrier ou heros.Magicien
     *
     * @return le choix selectionné sous forme de String
     */
    static String choixPersonnage() {
        String personnage;
        Scanner clavier = new Scanner(System.in);
        System.out.print("____________________Choisissez votre personnage (Guerrier ou Magicien) (stop pour quitter le jeu) :");
        personnage = clavier.nextLine();
        if (personnage.equals("stop")) {
            System.exit(0);
        }
        return personnage;
    }

    /**
     * Fonction qui permet au joueur de renseigner le nom de son personnage
     *
     * @return le nom choisi sous forme de String
     */
    static String choixDuNom() {
        String name;
        Scanner clavierName = new Scanner(System.in);
        System.out.print("____________________Quel est ton nom (stop pour quitter le jeu) :");
        name = clavierName.nextLine();
        if (name.equals("stop")) {
            System.exit(0);
        }
        return name;
    }

    /**
     * Fonction pour instancier un object de type heros.Guerrier
     *
     * @return l'objet G qui est un heros.Guerrier
     */
    static Guerrier CreateGuerrier() {
        String nomGuerrier;
        System.out.println("Tu as choisi de rejoindre nos rangs");
        nomGuerrier = choixDuNom();
        Guerrier G = new Guerrier(nomGuerrier, 5, 5, 10, 10);
        return G;
    }

    /**
     * Fonction pour instancier un object de type heros.Magicien
     *
     * @return l'object G qui est un heros.Magicien
     */
    static Magicien CreateMagicien() {
        String nomMagicien;
        System.out.println("Tu as choisi de rejoindre le clan des magiciens");
        nomMagicien = choixDuNom();
        Magicien G = new Magicien(nomMagicien, 3, 8, 6, 15);
        return G;
    }

    static Personnage createHeroe(String choixPersonnage) {
        Personnage player = null;
        while (!choixPersonnage.equals("Guerrier") && !choixPersonnage.equals("Magicien")) {
            System.out.println("Choix obligatoire : Guerrier ou Magicien");
            choixPersonnage = choixPersonnage();
        }
        if (choixPersonnage.equals("Guerrier")) {
            player = CreateGuerrier();
        } else if (choixPersonnage.equals("Magicien")) {
            player = CreateMagicien();
        }
        return player;
    }


    /**
     * Fonction de lancé de dé
     *
     * @return un integer qui contient le resultat du lancé de dé
     */


    static void imgVictoire() {
        System.out.print("                                  ___________\n" +
                "                             .---'::'        `---.\n" +
                "                            (::::::'              )\n" +
                "                            |`-----._______.-----'|\n" +
                "                            |              :::::::|\n" +
                "                           .|               ::::::!-.\n" +
                "                           \\|               :::::/|/\n" +
                "                            |               ::::::|\n" +
                "                            |      !VICTOIRE!    :|\n" +
                "                            |         BRAVO   ::::|\n" +
                "                            |               ::::::|\n" +
                "                            |              .::::::|\n" +
                "                            J              :::::::F\n" +
                "                             \\            :::::::/\n" +
                "                              `.        .:::::::'\n" +
                "                                `-._  .::::::-'\n" +
                "                                    |  \"\"\"|\"\n" +
                "                                    |  :::|\n" +
                "                                    F   ::J\n" +
                "                                   /     ::\\                                        \n" +
                "                              __.-'      :::`-.__\n" +
                "                             (_           ::::::_)\n" +
                "                               `\"\"\"---------\"\"\"'");
    }
}
