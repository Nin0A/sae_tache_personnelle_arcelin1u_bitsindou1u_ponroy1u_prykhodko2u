package classes;

import java.util.ArrayList;

//Classe Colonne
public class Colonne extends Composant<Tache> {



    //constructeur
    public Colonne(String n) {
        super(n);
    }


    /**
     * Méthode modifierDureeTache qui modifie la durée d'une tache
     * @param duree durée de la tache en jours
     * @param nomTache nom de la tache à modifier
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
    }

    /**
     * Méthode supprimerTache qui supprimer une tache à la liste de taches de la colonne
     * @param t tache à ajouter à la liste
     */
    public void supprimerTache(Tache t){

        // On vérifie si la tache est une TacheMere pour supprimer ses sous-taches si il n'y en a
        if (t instanceof TacheMere){
            TacheMere tM = (TacheMere) t;

            tM.reinitialiser();
        }else if (t instanceof SousTache){
            SousTache sT = (SousTache) t;

            // On supprime la tache de la liste de sous-taches de la tache mere
            getTacheMere(sT,null, this.liste).supprimerSousTache(sT);
        }

        // On supprime la tache de la liste de taches de la colonne
        this.liste.remove(t);

        //supprimer les dépendances de la tache supprimée
        for (Tache tache : this.liste){
            if (tache.avoirAntecedent(t)){
                tache.supprimerAntecedent(t);
            }
        }
    }

    /**
     * Méthode getTacheMere qui retourne la tache mère d'une sous-tache
     * @param tCherchee sous-tache dont on veut la tache mère
     * @param tM   tache mère de la sous-tache
     * @param list liste de taches
     * @return la tache mère de la sous-tache
     */
    public TacheMere getTacheMere(Tache tCherchee, TacheMere tM, ArrayList<Tache> list) {
        TacheMere tacheMere = null;
        System.out.println("début");
        boolean trouve = false;
        int i = 0;
        while (!trouve && i<list.size()){
                Tache tache = list.get(i);
            if (tache == tCherchee) {
                System.out.println("tache trouvée : " + tache+"tache mère : "+tM);
                tacheMere = tM;
                trouve=true;
            }else {
                if (tache instanceof TacheMere){
                    System.out.println("tache mere : " + tache);
                    TacheMere tachetmp = (TacheMere) tache;
                    tacheMere = getTacheMere(tCherchee,tachetmp,tachetmp.getSousTaches());
                }
            }
            i++;
        }
        return tacheMere;
    }

    /**
     * Méthode modifierNomDelaTache qui modifie le nom d'une tache
     * @param nomTacheAvant nom de la tache à modifier
     * @param nomTacheApres nouveau nom de la tache
     */
    public void modifierNomDelaTache(String nomTacheAvant, String nomTacheApres) {
        boolean tacheTrouvee = false; // on initialise un indicateur à faux pour savoir si la tache est trouvée ou non

        // On parcourt la liste de tâches de la colonne
        for (Tache t : this.liste) {
            if (t.getNom().equals(nomTacheAvant)) {
                t.setNom(nomTacheApres);
                System.out.println("Le nom de la tâche a été modifié");
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
     * Méthode afficher qui affiche toutes les taches de la colonne correctement indentées
     */
    public void afficher(){
        System.out.println(" -->" + this.nom+" :");
        for (Tache t : this.liste) {
            t.afficher();
        }
    }

}
