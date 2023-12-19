package classes;

public class Vue {

    private VueBureau vueBureau;
    private VueListe vueListe;
    private VueGantt vueGantt;
    private Observateur courant;

    Vue(Tableau tableau ){
        this.vueBureau = new VueBureau();
        tableau.enregistrerObservateur(vueBureau);
        this.vueListe = new VueListe();
        tableau.enregistrerObservateur(vueListe);
        this.vueGantt = new VueGantt();
        tableau.enregistrerObservateur(vueGantt);
        courant=vueBureau;
    }

    void changerVue(String s){

        switch (s){
            case "Vue Liste":
                courant= vueListe;
            break;
            case "Vue Gantt":
                courant= vueGantt;
                break;
            default:
                courant=vueBureau;
            break;


        }

        System.out.println(courant);

    }

    public Observateur getCourant(){
        System.out.println("CoURANTT "+courant);
        return courant;

    }
}
