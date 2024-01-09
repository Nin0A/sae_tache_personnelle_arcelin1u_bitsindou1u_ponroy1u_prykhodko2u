package classes;

import java.util.ArrayList;

//Classe Système

public class Systeme implements Sujet {

    //attributs
    private ArrayList<Tableau> listTab; //tableaux du système
    private Tableau tableauCourant; //tableau courant
    private ArrayList<Observateur> observateurs; //liste des observateurs du système

    //constructeur
    Systeme(){
        this.listTab =new ArrayList<Tableau>();
        observateurs = new ArrayList<>();
        this.tableauCourant = null;
    }


    /**
     * Méthode ajouterTab qui ajoute un tableau à la liste de tableaux
     * @param t tableau à ajouter à la liste
     */
   public void ajouterTab(Tableau t){
       if(tableauCourant == null){
           tableauCourant = t;
       }
       if(t!=null && !listTab.contains(t)){
       this.listTab.add(t);

       }
       notifierObservateur();
    }
    public void supprimerTab(Tableau t){
        listTab.remove(t);
        notifierObservateur();
    }
    public void changerTableauCourrant(Tableau t){
       if(t!=null && listTab.contains(t)){
        tableauCourant = t;
       }
        System.out.println("nouveau tab courant : "+tableauCourant.getNom());
        notifierObservateur();
    }
    /**
     * Méhtode enregistrerObservateur qui ajoute l'observateur
     * observé en paramètre à la liste d'observateurs
     * @param o observateur observé à ajouter
     */
    @Override
    public void enregistrerObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * Méhtode supprimerObservateur qui supprime l'observateur
     * observé en paramètre de la liste d'observateurs
     * @param o observateur observé à supprimer
     */
    @Override
    public void supprimerObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * Méhtode notifierObservateur qui met à jour l'observateur
     * observé en paramètre de la liste d'observateurs
     */
    @Override
    public void notifierObservateur() {
        for (Observateur obs : observateurs) {
            obs.actualiser(this);
        }
        for(Tableau tab : listTab){
            tab.notifierObservateur();
        }
    }
    /**
     * Méthode getTab qui retourne l'attribut le tableau
     * @return le tableau en question
     */
    public ArrayList<Tableau> getTableaux() {
        return listTab;
    }

    public Tableau getTableauCourant() {
        return tableauCourant;
    }
}
