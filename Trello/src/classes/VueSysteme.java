package classes;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VueSysteme extends VBox implements Observateur{

    @Override
    public void actualiser(Sujet sujet) {
        Systeme sys = (Systeme) sujet;
        System.out.println(sys.getTableaux().size());
        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.getChildren().clear();

        for(Tableau t : sys.getTableaux()){
            ControleurSysteme cs = new ControleurSysteme(sys, t);

            HBox zoneTab = new HBox();
            Button titre = new Button(t.getNom());
            titre.setOnAction(e -> {
                sys.changerTableauCourrant(t);
                sys.notifierObservateur();
            });
            this.getChildren().add(titre);


            Button modif = new Button("Modifier");
            modif.setOnAction(cs);
            zoneTab.getChildren().add(modif);

            Button suppr = new Button("Supprimer");
            suppr.setOnAction(cs);
            zoneTab.getChildren().add(suppr);
            this.getChildren().add(zoneTab);
        }
        Button ajouterTab = new Button("Ajouter Tableau");
        ajouterTab.setOnAction(new ControleurSysteme(sys, null));
        this.getChildren().add(ajouterTab);
    }
}
