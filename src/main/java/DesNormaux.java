public class DesNormaux implements Des {
    /**
     * Fonction pour lancer le dé
     *
     * @return un nombre aléatoire de 1 a 6
     */
    public int lancerD() {
        return (int) (Math.random() * 5) + 1;
    }
}
