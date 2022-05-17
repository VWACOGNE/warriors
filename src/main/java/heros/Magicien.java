package heros;

public class Magicien extends Personnage{
    // instance fields


    // constructor method
    /**
     *Assigne aux diférents parametres les valeurs
     * @param name
     * @param vie
     * @param force
     */
    public Magicien(String name, int vie, int force, int vMax, int fMax){
        this.setNomGuerrier(name);
        this.setVieGuerrier(vie);
        this.setForceGuerrier(force);
        this.setvMaxGuerrier(vMax);
        this.setfMaxGuerrier(fMax);
        this.setType("Magicien");

    }
    public Magicien(){
        this.setvMaxGuerrier(6);
        this.setfMaxGuerrier(15);
    }

    // main method


}
