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

    /**
     * Constructeur de la classe Vue
     * @param systeme système à observer
     */
    public Vue(Systeme systeme ){

        sujet = systeme;
        Tableau tableau = systeme.getTableauCourant();

        this.vueBureau = new VueBureau();
        this.vueListe = new VueListe();
        this.vueGantt = new VueGantt();
        this.vueArchive = new VueArchive();

        tableau.enregistrerObservateur(vueBureau);
        tableau.enregistrerObservateur(vueListe);
        tableau.enregistrerObservateur(vueGantt);
        tableau.enregistrerObservateur(vueArchive);

        courant = vueBureau;
        this.getChildren().clear();
        this.courant.actualiser(tableau);
        this.getChildren().add((Node)courant);
    }
    /**
     * Méthode changerVue qui change la vue courante
     * @param s nom de la vue à mettre en courant
     */
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
        Node node =(Node)courant;
        node.setStyle("-fx-background-color: transparent");
        this.getChildren().add((Node)courant);

    }
    /**
     * Méthode getCourant qui retourne l'observateur courant
     * @return l'observateur courant
     */
    public Observateur getCourant(){
        return courant;
    }
    /**
     * Méthode actualiser qui actualise la vue
     * @param sujet sujet à observer
     */
    @Override
    public void actualiser(Sujet sujet) {
        this.sujet.getTableauCourant().supprimerObservateur(vueBureau);
        this.sujet.getTableauCourant().supprimerObservateur(vueArchive);
        this.sujet.getTableauCourant().supprimerObservateur(vueGantt);
        this.sujet.getTableauCourant().supprimerObservateur(vueListe);
        this.sujet = (Systeme) sujet;

        Tableau tableau = this.sujet.getTableauCourant();
        this.vueBureau = new VueBureau();
        this.vueListe = new VueListe();
        this.vueGantt = new VueGantt();
        this.vueArchive = new VueArchive();

        tableau.enregistrerObservateur(vueBureau);
        tableau.enregistrerObservateur(vueListe);
        tableau.enregistrerObservateur(vueGantt);
        tableau.enregistrerObservateur(vueArchive);

        this.getChildren().clear();
        this.courant.actualiser(tableau);
        this.getChildren().add((Node)courant);

    }
}
