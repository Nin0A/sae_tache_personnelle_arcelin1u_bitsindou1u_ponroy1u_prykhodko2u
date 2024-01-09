package classes;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class Vue extends HBox implements Observateur  {

    private VueBureau vueBureau;
    private VueListe vueListe;
    private VueGantt vueGantt;
    private VueArchive vueArchive;
    private Observateur courant;
    private  Systeme sujet;

    public Vue(Systeme systeme ){
        sujet = systeme;
        Tableau tableau = systeme.getTableauCourant();
        this.vueBureau = new VueBureau();
        tableau.enregistrerObservateur(vueBureau);
        this.vueListe = new VueListe();
        tableau.enregistrerObservateur(vueListe);
        this.vueGantt = new VueGantt();
        tableau.enregistrerObservateur(vueGantt);
        this.vueArchive = new VueArchive();
        tableau.enregistrerObservateur(vueArchive);
        courant=vueBureau;
    }

    public void changerVue(String s){
        this.getChildren().clear();
        switch (s){
            case "Vue Liste":
                courant = vueListe;
            break;
            case "Vue Gantt":
                courant = vueGantt;
                break;
            case "Vue Archive":
                courant = vueArchive;
                break;
            default:
                courant = vueBureau;
                break;
        }
        this.getChildren().add((Node)courant);

    }

    public Observateur getCourant(){
        return courant;
    }

    @Override
    public void actualiser(Sujet sujet) {
        this.sujet.supprimerObservateur(vueBureau);
        this.sujet.supprimerObservateur(vueArchive);
        this.sujet.supprimerObservateur(vueGantt);
        this.sujet.supprimerObservateur(vueListe);
        this.sujet = (Systeme) sujet;

        Tableau tableau = this.sujet.getTableauCourant();
        tableau.enregistrerObservateur(vueBureau);
        tableau.enregistrerObservateur(vueListe);
        tableau.enregistrerObservateur(vueGantt);
        tableau.enregistrerObservateur(vueArchive);

        this.getChildren().clear();
        this.getChildren().add((Node)courant);
        this.courant.actualiser(tableau);
    }
}
