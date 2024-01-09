package classes;

public class SousTache extends Tache{


    //attributs
    //private TacheMere tacheMere; tache mere de la sous-tache

    public SousTache(String desc,Colonne c, int duree, int jour, int mois, int annee) {
        super(desc, c,duree, jour, mois, annee);
        //this.tacheMere = tacheMere;
    }

    /**
     * Méthode getTacheMere qui retourne la tache mere de la sous-tache
     * @return la tache mere de la sous-tache
     */
    /*public TacheMere getTacheMere() {
        return tacheMere;
    }*/
}
