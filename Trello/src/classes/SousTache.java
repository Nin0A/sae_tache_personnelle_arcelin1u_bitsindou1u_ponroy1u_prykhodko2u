package classes;

public class SousTache extends Tache{

    //attributs
    private TacheMere tacheMere; //tache mere de la sous-tache

    public SousTache(String desc, int duree, TacheMere tacheMere) {
        super(desc, duree);
        this.tacheMere = tacheMere;
    }

    /**
     * Méthode getTacheMere qui retourne la tache mere de la sous-tache
     * @return la tache mere de la sous-tache
     */
    public TacheMere getTacheMere() {
        return tacheMere;
    }
}
