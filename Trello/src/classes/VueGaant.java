package classes;

public class VueGaant implements Observateur {
    @Override
    public void actualiser(Sujet sujet) {
        Tableau tab = (Tableau) sujet;
        System.out.println("VueGaant : " + tab.getNom());
        //TODO

    }
}
