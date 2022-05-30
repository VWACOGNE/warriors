import heros.Personnage;

public class CaseEpee extends CaseEquipement{


    public CaseEpee() {
        setForce(5);
    }
    /**
     * Méthode pour utiliser l'équipement
     * @param player
     */
    public void utiliser(Personnage player){
        if (player.getClass().getSimpleName().equals("Guerrier")){
            if ((player.getForceGuerrier() + getForce()) >= player.getfMaxGuerrier()) {
                player.setForceGuerrier(player.getfMaxGuerrier());
                System.out.println("tu as atteint la force max");
            } else {
                player.setForceGuerrier(player.getForceGuerrier() + getForce());
                System.out.println("tu as gagné " + getForce() + " points de force");
            }
        }

    }
}
