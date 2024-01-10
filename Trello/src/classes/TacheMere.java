package classes;

import javafx.scene.input.DataFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

//Classe TacheMere

public class TacheMere extends Tache {

    //attributs
    private ArrayList<Tache> sousTaches; //liste de sous-taches

    public TacheMere(Tache t){
        super(t.nom,t.getColonneOrigine() ,t.getDuree(), t.getDateDebut().getDayOfMonth() ,t.getDateDebut().getMonthValue(), t.getDateDebut().getYear());
        sousTaches = new ArrayList<>(((TacheMere)t).getSousTaches());
    }



    //constructeur
    public TacheMere(String desc,Colonne colonne, double duree, int jour, int mois, int annee) {
        super(desc,colonne,duree, jour, mois, annee);
        sousTaches = new ArrayList<Tache>();
    }

    //constructeur par copie: pour transformer une sous tache en tache mere
    public TacheMere (TacheMere t){
        super(t.getNom(),t.getColonneOrigine(),t.getDuree(),t.getDateDebut().getDayOfMonth(),t.getDateDebut().getMonthValue(),t.getDateDebut().getYear(),t.idTache);
        sousTaches = t.sousTaches;
    }
    public TacheMere (SousTache t){
        super(t.getNom(),t.getColonneOrigine(),t.getDuree(),t.getDateDebut().getDayOfMonth(),t.getDateDebut().getMonthValue(),t.getDateDebut().getYear(),t.idTache);
        sousTaches = new ArrayList<Tache>();
    }

    /**
     * Méthode ajouterSousTache qui ajoute une tache à la liste de sous-taches
     * @param t tache à ajouter à la liste de sous-tache
     */
    public void ajouterSousTache(Tache t){
        //on vérifie que la sous tache n'est pas déjà dans la liste
//        boolean res = false;

        if(verifAjout(t) && t!=null && !this.sousTaches.contains(t)) {
            this.sousTaches.add(t);
        }
//            this.sousTaches.add(t);
//            System.out.println("REMOVED");
//        return res;
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

    /**
     * Méthode afficher qui affiche la tache et toutes ses sous-taches
     */
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
     * Méthode tacheById qui retourne la tache qui a l'id passé en paramètre
     * @param id id de la tache recherchée
     * @return la tache qui a l'id passé en paramètre
     */
    public Tache tacheById(int id){
        if(this.idTache==id){
            return this;
        }else{
            Tache res = null;
            for(Tache st: sousTaches){
                res = st.tacheById(id);
                if(res!=null)
                    break;
            }
            return res;
        }
    }
    /**
     * Méthode reinitialiser qui supprime toutes les sous-taches de la tache
     */
    public void reinitialiser(){
        sousTaches = new ArrayList<Tache>();
    }

    /**
     * Méthode verifDureeSousTaches qui vérifie que la somme des durées des sous taches soit inférieure ou égale à la
     * durée de la tache mère
     * @return true si la durée des sous taches est corrcte, false sinon
     */
    public boolean verifDureeSousTaches(Tache tache) {
        //on verifie que chaque sous tache n'ait pas la meme date de debut et que la somme des durées soient
        // inférieurs ou égales à la durée de la tache mère
        boolean res = true;
        double duree = 0;
        for (Tache t : sousTaches) {
            //on additionne les durées des sous taches
            duree += t.getDuree();
        }
        if (tache!=null)
            duree += tache.getDuree();
        if (duree > this.getDuree()) {
            res = false;
        }
        return res;
    }

    /**
     * Méthode verifDateDebutSousTaches qui vérifie que la plus petite date de début des sous taches soit égale ou
     * supérieure à la date de début de la tache mère
     * @return true si les dates des sous taches sont correctes, false sinon
     */
    public boolean verifDateDebutSousTaches(Tache t) {
        //on cherche la sous tache de la liste avec la date de debut la plus petite

        //on trie d'abord la liste de sous taches par date de debut
        boolean res = true;
        ArrayList<Tache> listeTrie = new ArrayList<Tache>();
        //on ajoute la premiere sous tache de la liste
        int start = 0;
        if (t!=null)
            listeTrie.add(t);
        else {
            listeTrie.add(sousTaches.get(0));
            start=1;
        }
        for (int i = start; i < sousTaches.size(); i++) {
            Tache tacheCourante = sousTaches.get(i);
            int j = 0;
            //on cherche la position de la sous tache dans la liste triée
            while (j < listeTrie.size() && tacheCourante.getDateDebut().isAfter(listeTrie.get(j).getDateDebut())) {
                j++;
            }
            listeTrie.add(j, tacheCourante);
        }

        //on verifie que la date de la première sous tache soit égale ou supérieure à la date de debut de la tache mère
        if (listeTrie.get(0).getDateDebut().isBefore(this.getDateDebut())) {
            res = false;
        }
        return res;

    }

    /**
     * Méthode verifDateFinSousTaches qui vérifie que la plus grande date de fin des sous taches soit égale ou
     * inférieure à la date de fin de la tache mère
     * @return true si les dates des sous taches sont correctes, false sinon
     */
    public boolean verifDateFinSousTaches(Tache t) {
        //on cherche la sous tache de la liste avec la date de fin la plus grande

        //on trie d'abord la liste de sous taches par date de fin
        boolean res = true;
        //on trie d'abord la liste de sous taches par date de fin
        ArrayList<Tache> listeTrie = new ArrayList<Tache>();
        //on ajoute la premiere sous tache de la liste
        int start = 0;
        if (t!=null)
            listeTrie.add(t);
        else {
            listeTrie.add(sousTaches.get(0));
            start=1;
        }
        for (int i = start; i < sousTaches.size(); i++) {
            Tache tacheCourante = sousTaches.get(i);
            int j = 0;
            //on cherche la position de la sous tache dans la liste triée
            while (j < listeTrie.size() && tacheCourante.getDateFin().isAfter(listeTrie.get(j).getDateFin())) {
                j++;
            }
            listeTrie.add(j, tacheCourante);
        }
        //on verifie que la date de la dernière sous tache soit égale ou inférieure à la date de fin de la tache mère
        if (listeTrie.get(listeTrie.size()-1).getDateFin().isAfter(this.getDateFin())) {
            res = false;
        }
        return res;
    }

    /**
     * Méthode verifChevauche qui verifie que la durée d'une sous tache ne chevauche pas sur celle d'une autre
     * @return true si les sous taches ne se chevauchent pas, false sinon
     */
    
    public boolean verifChevauche(Tache tache) {
        ArrayList<Tache> clone = new ArrayList<>(sousTaches);
        clone.add(tache);

        for (int i = 0; i < clone.size(); i++) {
            Tache t1 = clone.get(i);
            LocalDate dateFinT1 = t1.getDateDebut().plusDays((long) t1.getDuree());

            for (int j = i + 1; j < clone.size(); j++) {
                Tache t2 = clone.get(j);
                LocalDate dateDebutT2 = t2.getDateDebut();

                if ( !dateFinT1.equals(dateDebutT2) && !dateFinT1.isBefore(dateDebutT2)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Méthode verifSousTaches qui vérifie que les sous taches soient correctes
     * @return true si les sous taches sont correctes, false sinon
     */
    public boolean verifSousTaches(Tache t) {
        return verifDureeSousTaches(t) && verifDateDebutSousTaches(t) && verifDateFinSousTaches(t) && verifChevauche(t);
    }
    /**
     * Méthode verifAjout qui vérifie que l'ajout d'une sous tache soit correcte
     * @param t tache à ajouter
     * @return true si l'ajout est correcte, false sinon
     */
    public boolean verifAjout(Tache t){

        System.out.println(verifDureeSousTaches(t));
        System.out.println(verifDateDebutSousTaches(t));
        System.out.println(verifDateFinSousTaches(t));
        System.out.println(verifChevauche(t));
        return verifSousTaches(t);
    }

}
