package classes;

import java.util.ArrayList;

//Classe TacheMere

public class TacheMere extends Tache {

    //attributs
    private ArrayList<Tache> sousTaches; //liste de sous-taches

    //constructeur
    public TacheMere(String desc, double duree, int jour, int mois, int annee) {
        super(desc,duree, jour, mois, annee);
        sousTaches = new ArrayList<Tache>();
    }

    //constructeur par copie: pour transformer une sous tache en tache mere
    public TacheMere (SousTache t){
        super(t.getNom(),t.getDuree(),t.getDateDebut().getDayOfMonth(),t.getDateDebut().getMonthValue(),t.getDateDebut().getYear());
        sousTaches = new ArrayList<Tache>();
    }

    /**
     * Méthode ajouterSousTache qui ajoute une tache à la liste de sous-taches
     * @param t tache à ajouter à la liste de sous-tache
     */
    public void ajouterSousTache(Tache t){
        //on vérifie que la sous tache n'est pas déjà dans la liste
        if (!this.sousTaches.contains(t)&& t!=null) {
            this.sousTaches.add(t);
        }
    }

    /**
     * Méthode supprimerSousTache qui supprimer une tache de la liste de sous-taches
     * @param t tache à supprimer de la liste de sous-tache
     */
    public void supprimerSousTache(Tache t){
        this.sousTaches.remove(t);
    }

    /**
     * Méthode getSousTaches qui retourne la liste de toutes les sous-tâches de la tache
     * @return la liste de sous-taches de la tache
     */
    public ArrayList<Tache> getSousTaches() {
        return sousTaches;
    }

    @Override
    public void afficher() {
        super.afficher();
        for (Tache t : sousTaches) {
            System.out.print("\t");
            t.afficher();
        }
    }

    /**
     * Méthode tacheExiste le nom est utilisée
     * @param t nom recherché
     * @return true si le nom est utilisé, false sinon
     */
    public boolean tacheExiste(String t){
        boolean res = this.nom.equals(t);
        for(Tache tache : sousTaches){
            if(tache.tacheExiste(t)){
                res = true;
            }
        }
        return res;
    }

    /**
     * Méthode reinitialiser qui supprime toutes les sous-taches de la tache
     */
    public void reinitialiser(){
        sousTaches = new ArrayList<Tache>();
    }
}
