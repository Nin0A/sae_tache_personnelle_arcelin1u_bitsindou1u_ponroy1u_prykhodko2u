package classes;
import classes.Observateur;
import classes.Sujet;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VueGantt extends Pane implements Observateur {

    private Tableau tableau;
    private static final int TAILLE = 20;

    private void buildGantt() {
        this.getChildren().clear();

        LocalDate baseDate = determineBaseDate(tableau);

        HBox timeLine = createTimeLine();
        this.getChildren().add(timeLine);

        int yPos = 20;
        for (Colonne colonne : tableau.getColonnes()) {
            for (Tache tache : colonne.getTaches()) {
                Rectangle rect = createTaskRectangle((TacheMere) tache, yPos, baseDate);
                this.getChildren().add(rect);
                yPos += TAILLE + 10;
            }
        }
    }


    private LocalDate determineBaseDate(Tableau tableau) {
        LocalDate baseDate = LocalDate.MAX;
        for (Colonne colonne : tableau.getColonnes()) {
            for (Tache tache : colonne.getTaches()) {
                LocalDate startDate = tache.getDateDebut();
                if (startDate.isBefore(baseDate)) {
                    baseDate = startDate;
                }
            }
        }
        return baseDate;
    }



    private HBox createTimeLine() {
        HBox timeLine = new HBox();
        for (int day = 0; day < 30; day++) {
            Label dayLabel = new Label();
            dayLabel.setText(String.valueOf(day + 1));
            timeLine.getChildren().add(dayLabel);
        }
        return timeLine;
    }

    private Rectangle createTaskRectangle(TacheMere tache, int yPos, LocalDate baseDate) {
        LocalDate startDate = tache.getDateDebut();
        long startOffset = ChronoUnit.DAYS.between(baseDate, startDate);

        double duration = tache.getDuree();

        Rectangle rect = new Rectangle(startOffset * TAILLE, yPos, duration * TAILLE, 20);
        rect.setFill(Color.BLUE);
        rect.setStroke(Color.BLACK);

        return rect;
    }

    @Override
    public void actualiser(Sujet sujet) {
        if (sujet instanceof Tableau) {
            Tableau tableau = (Tableau) sujet;
            this.tableau = tableau;
            buildGantt();
        }
    }
}
