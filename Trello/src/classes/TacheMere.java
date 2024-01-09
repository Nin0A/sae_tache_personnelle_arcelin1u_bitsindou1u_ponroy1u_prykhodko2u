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
        for(Tache tache : sousTaches){
            if (tache instanceof TacheMere){
                ((TacheMere) tache).supprimerSousTache(t);
            }
        }
    }

    @Override
    public void supprimerAntecedent(Tache t) {
        super.supprimerAntecedent(t);
        for(Tache tache : sousTaches){
            tache.supprimerAntecedent(t);
        }
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
     * Méthode tacheExiste qui vérifie si une tache existe dans la liste de sous-taches
     * @param id Id recherché
     * @return true si le nom est utilisé, false sinon
     */
    public boolean tacheExiste(int id){
        boolean res = this.idTache == id;
        for(Tache tache : sousTaches){
            if(tache.tacheExiste(id)){
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

    /**
     * Méthode verifSousTaches qui vérifie que chaque sous tache ait une date de debut et une durée differente
     * @return true si les sous taches sont correctes, false sinon
     */
    public boolean verifSousTaches() {
        boolean res = true;
        for (Tache t : sousTaches) {
            if (t.getDateDebut().equals(this.getDateDebut()) ) {
                res = false;
            }
        }
        return res;
    }

    /**
     * Méthode verifChevauche qui verifie que la durée d'une sous tache ne chevauche pas sur celle d'une autre
     * @return true si les sous taches ne se chevauchent pas, false sinon
     */
    public boolean verifChevauche() { //A FINIR
        boolean res = true;
        Tache tacheCourante;
        for (Tache t : sousTaches) {
            tacheCourante = t;
            //on verifie que la somme de la durée et de la date de debut d'une sous tache ne chevauche pas sur la date de debut d'une autre sous tache
            for (Tache t2 : sousTaches) {
                if (tacheCourante != t2) {
                    if (tacheCourante.getDateDebut().plusDays((long) tacheCourante.getDuree()).isAfter(t2.getDateDebut()) && tacheCourante.getDateDebut().isBefore(t2.getDateDebut())) {
                        res = false;
                    }
                }
            }
        }
        return res;
    }
}
