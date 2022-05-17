package heros;

public class Guerrier extends Personnage {
    // instance fields


    // constructor method

    /**
     *Assigne aux diférents parametres les valeurs
     * @param name
     * @param vie
     * @param force
     */
    public Guerrier(String name, int vie, int force, int vMax, int fMax){
        this.setNomGuerrier(name);
        this.setVieGuerrier(vie);
        this.setForceGuerrier(force);
        this.setvMaxGuerrier(vMax);
        this.setfMaxGuerrier(fMax);
        this.setType("Guerrier");

    }
    public Guerrier(){
        this.setvMaxGuerrier(10);
        this.setfMaxGuerrier(10);
    }


    // main method

}
