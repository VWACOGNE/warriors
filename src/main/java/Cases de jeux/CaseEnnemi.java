import heros.Personnage;

public class CaseEnnemi {

    /**
     * Assigne aux parametres les valeurs
     * @param player
     * @param type
     * @param plateau
     */
    public CaseEnnemi(Personnage player, Plateau.Cases type, Plateau plateau) {
        Ennemi ennemi = null;
        if (type == Plateau.Cases.GOBELIN) {
            ennemi = new Gobelin();
        } else if (type == Plateau.Cases.SORCIER) {
            ennemi = new Sorcier();
        } else if (type == Plateau.Cases.DRAGON) {
            ennemi = new Dragon();
        }
        combat(ennemi, player, plateau);

        imgVictoireCombat();
    }

    /**
     * Fonction pour executer le combat
     * @param ennemi
     * @param player
     * @param plateau
     */
    public void combat(Ennemi ennemi, Personnage player, Plateau plateau){
        while (ennemi.getPointDeVie()>0) {
            ennemi.setPointDeVie(ennemi.getPointDeVie() - player.getForceGuerrier());
            System.out.println("Sa vie : " + ennemi.getPointDeVie());
            if (ennemi.getPointDeVie() > 0) {
                player.setVieGuerrier(player.getVieGuerrier() - ennemi.getPointAttaque());
                System.out.println("Ta vie : " + player.getVieGuerrier());
            }
            if (player.getVieGuerrier() <= 0) {
                System.out.println("__________Votre position est maintenant sur la case : " + (plateau.getPosition() + 1));
                System.out.println(player);
                imgGameOver();
                System.exit(0);
            }
        }
    }

    /**
     * Affiche "Game Over"
     */
    public void imgGameOver() {
        System.out.println(
                "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀\n" +
                        "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼\n" +
                        "██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀\n" +
                        "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼\n" +
                        "███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄\n" +
                        "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                        "███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼\n" +
                        "██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                        "███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄");
    }

    /**
     * Affiche "combat gagné"
     */
    public void imgVictoireCombat() {
        System.out.println(" ██████╗ ██████╗ ███╗   ███╗██████╗  █████╗ ████████╗     ██████╗  █████╗  ██████╗ ███╗   ██╗███████╗\n" +
                "██╔════╝██╔═══██╗████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝    ██╔════╝ ██╔══██╗██╔════╝ ████╗  ██║██╔════╝\n" +
                "██║     ██║   ██║██╔████╔██║██████╔╝███████║   ██║       ██║  ███╗███████║██║  ███╗██╔██╗ ██║█████╗  \n" +
                "██║     ██║   ██║██║╚██╔╝██║██╔══██╗██╔══██║   ██║       ██║   ██║██╔══██║██║   ██║██║╚██╗██║██╔══╝  \n" +
                "╚██████╗╚██████╔╝██║ ╚═╝ ██║██████╔╝██║  ██║   ██║       ╚██████╔╝██║  ██║╚██████╔╝██║ ╚████║███████╗\n" +
                " ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═════╝ ╚═╝  ╚═╝   ╚═╝        ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝\n" +
                "                                                                                                     ");
    }

}
