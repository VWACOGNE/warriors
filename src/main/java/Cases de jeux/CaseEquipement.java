import heros.Personnage;

public abstract class CaseEquipement {

    private int force;
    private int vie;

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getForce() {
        return force;
    }
    public void setForce(int force) {
        this.force = force;
    }

    /**
     * Méthode abstract pour utiliser l'équipement
     * @param player
     */
    public abstract void utiliser(Personnage player);




}
