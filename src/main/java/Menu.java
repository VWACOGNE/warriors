import heros.Guerrier;
import heros.Magicien;
import heros.Personnage;

import java.util.List;
import java.util.Scanner;

public class Menu {


    public static void main(String[] args) {
        Bdd bdd = new Bdd();
        List<Personnage> listPersoSauve = bdd.recupererUtilisateurs();
        Personnage player;
        String sauvegarde = choixClavier("Veux-tu un personnage sauvegardé ? (o pour oui n pour non)");
        if (sauvegarde.equals("o")) {
            for (int i = 0; i < listPersoSauve.size(); i++) {
                System.out.println(i);
                System.out.println(listPersoSauve.get(i));
            }
            int choixList;
            Scanner clavier = new Scanner(System.in);
            System.out.print("quel perso veux-tu ? (donnes un numero)");
            choixList = clavier.nextInt();

            player = listPersoSauve.get(choixList);

        } else {
            player = null;
            boolean nomChoisit = false;
            while (!nomChoisit) {
                player = createHeroe(choixPersonnage());
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
            bdd.ajouterUtilisateur(player);
            System.out.print("____________________Votre personnage est sauvegardé");

        }
        System.out.println(player);
        Plateau P = new Plateau(bdd, player);
//            System.out.print("____________________Votre position sur le plateau : case 1 \n");
        P.avancementPlateau(player);
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
    static String whatIsYourName() {
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
        nomGuerrier = whatIsYourName();
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
        nomMagicien = whatIsYourName();
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
    public static int lancerD() {
        return (int) (Math.random() * 5) + 1;
    }

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
