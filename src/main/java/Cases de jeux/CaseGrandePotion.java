import heros.Personnage;

public class CaseGrandePotion extends CaseEquipement {
    CaseGrandePotion(){
        setVie(5);
    }

    /**
     * Méthode pour utiliser l'équipement
     * @param player
     */
    public void utiliser(Personnage player){
        if ((player.getVieGuerrier() + getVie()) >= player.getvMaxGuerrier()) {
            player.setVieGuerrier(player.getvMaxGuerrier());
            System.out.println("Tu as atteint la vie max");
        } else {
            player.setVieGuerrier(player.getVieGuerrier() + getForce());
            System.out.println("Tu as gagné " + getVie() + " points de vie");
        }
    }
}
