import heros.Personnage;

public class CasePotion implements Case{

    private int potionDeVie = 2;
    private int megaPotionDeVie = 5;

    public void casePotion(Personnage player, Plateau.Cases type) {

        if (type == Plateau.Cases.PETITE_POTION) {
            action(player, getPotionDeVie());
        } else if (type == Plateau.Cases.GRANDE_POTION) {
            action(player, getMegaPotionDeVie());
        }
    }

    public void action(Personnage player, int potion) {

            if ((player.getVieGuerrier() + potion) >= player.getvMaxGuerrier()) {
                player.setVieGuerrier(player.getvMaxGuerrier());
                System.out.println("Tu as atteint la vie max");
            } else {
                player.setVieGuerrier(player.getVieGuerrier() + potion);
                System.out.println("Tu as gagné " + potion + " points de vie");
            }
    }





    public int getPotionDeVie() {
        return potionDeVie;
    }

    public int getMegaPotionDeVie() {
        return megaPotionDeVie;
    }

}
