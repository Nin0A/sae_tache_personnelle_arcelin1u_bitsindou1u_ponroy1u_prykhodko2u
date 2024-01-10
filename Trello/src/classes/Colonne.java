package classes;

import java.util.ArrayList;
import java.util.Objects;

//Classe Colonne
public class Colonne extends Composant<Tache> {

    private static int nbColonnes = 0;
    private int idColonne;

    //constructeur
    public Colonne(String n) {
        super(n);
        nbColonnes++;
        idColonne = nbColonnes;
    }


    /**
     * Méthode modifierDureeTache qui modifie la durée d'une tache
     * @param duree durée de la tache en jours
     * @param nomTache nom de la tâche à modifier
     */
    public boolean modifierDureeTache(int duree, String nomTache){
        if (duree<0)
            return false;
        for (Tache t : this.liste){
            if (t.nom.equals(nomTache)){
                t.setDuree(duree);
            }
        }
        return true;
    }

    /**
     * Méthode ajouterTache qui ajoute une tache à la liste de taches de la colonne
     * @param t tache à ajouter à la liste
     */
    public void ajouterTache(Tache t){
        this.liste.add(t);
        t.setColonneOrigine(this);
    }

    /**
     * Méthode supprimerTache qui supprimer une tache à la liste de taches de la colonne
     * @param t tache à ajouter à la liste
     */
    public void supprimerTache(Tache t){
        //on supprime la tache de la liste
        //on cherche la tache
        for (Tache tache : this.liste){
            if (tache instanceof TacheMere){
                ((TacheMere) tache).supprimerSousTache(t);
            }
            if (tache.equals(t)){
                this.liste.remove(tache);
                break;
            }

        }

        //on supprime tout les liens avec la tache à supprimer
        supprimerOcurance(t);

    }
    /**
     * Méthode supprimerOcurance qui supprime une tache de la liste des antécédents et des SousTaches de toutes les taches de la colonne
     * @param tache tache à supprimer
     */
    public void supprimerOcurance(Tache tache){
        for(Tache tachetmp : liste){
            tachetmp.supprimerAntecedent(tache);
            if(tachetmp instanceof TacheMere){
                ((TacheMere) tachetmp).supprimerSousTache(tache);
            }
        }
    }

    /**
     * Méthode getTacheMere qui retourne la tache mère d'une sous-tache
     * @param tCherchee sous-tache dont on veut la tache mère
     * @param list liste de taches
     * @return la tache mère de la sous-tache
     */
    public TacheMere getTacheMere(Tache tCherchee, ArrayList<Tache> list) {
        TacheMere tMere = null;
        // On parcourt la liste de taches
        for (Tache t : list) {

            // Si la tache est une tache mère
            if (t instanceof TacheMere) {
                TacheMere tM = (TacheMere) t;

                // Si la tache mère contient la sous-tache cherchée
                if (tM.getSousTaches().contains(tCherchee)) {
                    tMere = tM;
                } else {
                    // Si la tache mère contient des sous-taches
                    if (tM.getSousTaches().size() > 0) {
                        tMere = getTacheMere(tCherchee, tM.getSousTaches());
                    }
                }
            }
        }

        return tMere;
    }

    /**
     * Méthode modifierNomDelaTache qui modifie le nom d'une tache
     * @param tache la tache à modifier
     * @param nomTache nouveau nom de la tache
     */
    public void modifierNomDelaTache(Tache tache, String nomTache) {
        boolean tacheTrouvee = false; // on initialise un indicateur à faux pour savoir si la tache est trouvée ou non

        // On parcourt la liste de tâches de la colonne
        for (Tache t : this.liste) {
            if (t.equals(tache)) {
                t.setNom(nomTache);
                tacheTrouvee = true; // on met l'indicateur à vrai si la tache est trouvée
            }
        }

        // Lève une exception si la tâche n'a pas été trouvée après avoir parcouru toute la liste
        if (!tacheTrouvee) {
            throw new IllegalArgumentException("Le nom de la tâche n'est pas dans la liste");
        }
    }


    /**
     * Méthode toString qui affiche le nom de la colonne
     * @return l'affichage du nom de la colonne
     */
    @Override
    public String toString() {
        return this.nom;
    }

    /**
     * Méthode getTaches qui retourne la liste de toutes les tâches de la colonne this
     * @return la liste de taches de la colonne
     */
    public ArrayList<Tache> getTaches() {
        return liste;
    }
    /**
     * Méthode getIdColonne qui retourne l'id de la colonne
     * @return l'id de la colonne
     */
    public int getIdColonne() {
        return idColonne;
    }

    /**
     * Méthode equals
     * @param o objet avec lequel on compare
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Colonne colonne = (Colonne) o;
        return idColonne == colonne.getIdColonne();
    }
    /**
     * Méthode hashCode
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(getIdColonne());
    }

    /**
     * Méthode afficher qui affiche toutes les taches de la colonne correctement indentées
     */
    public void afficher(){
        System.out.println(" -->" + this.nom+" :");
        for (Tache t : this.liste) {
            t.afficher();
        }
    }

}
