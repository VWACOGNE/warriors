package heros;

public class Personnage {
    private String nomGuerrier;
    private int vieGuerrier;
    private int forceGuerrier;
    private String type;
    private int vMaxGuerrier;
    private int fMaxGuerrier;


    /**
     * Methode pour afficher les parametres du personnage
     *
     * @return
     */
    public String toString() {
        return (("- Ton nom : " + getNomGuerrier()) + ("\n- Niveau de vie : " + getVieGuerrier()) + ("\n- Niveau de force : " + getForceGuerrier()));
    }






    public int getfMaxGuerrier() {
        return fMaxGuerrier;
    }

    public void setfMaxGuerrier(int fMaxGuerrier) {
        this.fMaxGuerrier = fMaxGuerrier;
    }

    public int getvMaxGuerrier() {
        return vMaxGuerrier;
    }

    public void setvMaxGuerrier(int vMaxGuerrier) {
        this.vMaxGuerrier = vMaxGuerrier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNomGuerrier() {
        return nomGuerrier;
    }

    public void setNomGuerrier(String nomGuerrier) {
        this.nomGuerrier = nomGuerrier;
    }

    public int getVieGuerrier() {
        return vieGuerrier;
    }

    public void setVieGuerrier(int vieGuerrier) {
        this.vieGuerrier = vieGuerrier;
    }

    public int getForceGuerrier() {
        return forceGuerrier;
    }

    public void setForceGuerrier(int forceGuerrier) {
        this.forceGuerrier = forceGuerrier;
    }


}
