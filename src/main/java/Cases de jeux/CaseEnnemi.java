import heros.Personnage;

public class CaseEnnemi {
    private int pointDeVie;
    private int pointAttaque;



    public int getPointDeVie() {
        return pointDeVie;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public int getPointAttaque() {
        return pointAttaque;
    }

    public void setPointAttaque(int pointAttaque) {
        this.pointAttaque = pointAttaque;
    }

    /**
     * Méthode pour faire le combat
     * @param player
     * @param plateau
     */
    public void faireLeCombat(Personnage player, Plateau plateau){
        while (getPointDeVie()>0) {
            setPointDeVie(getPointDeVie() - player.getForceGuerrier());
            System.out.println("Sa vie : " + getPointDeVie());
            if (getPointDeVie() > 0) {
                player.setVieGuerrier(player.getVieGuerrier() - getPointAttaque());
                System.out.println("Ta vie : " + player.getVieGuerrier());
            }
            if (player.getVieGuerrier() <= 0) {
                System.out.println("__________Votre position est maintenant sur la case : " + (plateau.getPosition() + 1));
                System.out.println(player);
                imgGameOver();
                System.exit(0);
            }
        }
        imgVictoireCombat();
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
