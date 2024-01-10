package classes;

public class SousTache extends Tache{

    /**
     * Constructeur de SousTache
     * @param desc description de la tache
     * @param c colonne de la tache
     * @param duree durée de la tache
     * @param jour jour de la tache
     * @param mois mois de la tache
     * @param annee année de la tache
     */
    public SousTache(String desc,Colonne c, int duree, int jour, int mois, int annee) {
        super(desc, c,duree, jour, mois, annee);
    }

}
