import heros.Personnage;

public class CaseEclair extends CaseEquipement{
    public CaseEclair() {
        setForce(2);
    }
    /**
     * Méthode pour utiliser l'équipement
     * @param player
     */
    public void utiliser(Personnage player){
        if (player.getClass().getSimpleName().equals("Magicien")){
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
