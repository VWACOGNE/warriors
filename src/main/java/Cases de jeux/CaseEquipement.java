import heros.Personnage;

public class CaseEquipement {
    private int massue = 3;
    private int epee = 5;
    private int eclair = 2;
    private int bouleDeFeu = 7;


    public void caseEquipement(Personnage player, Plateau.Cases type) {
        if (player.getType().equals("Guerrier")) {
            if (type == Plateau.Cases.MASSUE) {
                equipement(player, getMassue());
            } else if (type == Plateau.Cases.EPEE) {
                equipement(player, getEpee());
            }
        } else {
            if (type == Plateau.Cases.ECLAIR) {
                equipement(player, getEclair());
            } else if (type == Plateau.Cases.BOULE_FEU){
                equipement(player, getBouleDeFeu());
            }
        }
    }

    static void equipement(Personnage player, int arme) {
        if ((player.getForceGuerrier() + arme) >= player.getfMaxGuerrier()) {
            player.setForceGuerrier(player.getfMaxGuerrier());
            System.out.println("tu as atteint la force max");
        } else {
            player.setForceGuerrier(player.getForceGuerrier() + arme);
            System.out.println("tu as gagné " + arme + " points de force");
        }
    }



    public int getEclair() {
        return eclair;
    }

    public int getBouleDeFeu() {
        return bouleDeFeu;
    }

    public int getMassue() {
        return massue;
    }

    public int getEpee() {
        return epee;
    }
}
