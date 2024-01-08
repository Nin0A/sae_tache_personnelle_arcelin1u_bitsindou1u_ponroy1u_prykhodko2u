package classes;

import javafx.scene.control.Label;

public class VueNomTableau extends Label implements Observateur {
    VueNomTableau(Systeme systeme){
        super(systeme.getTableauCourant().getNom());
        this.setStyle("-fx-font-family: Krungthep;-fx-font-size: 20;");
    }
    @Override
    public void actualiser(Sujet sujet) {
        Systeme sys = (Systeme) sujet;
        Tableau tab = sys.getTableauCourant();
        this.setText(tab.getNom());
    }
}
