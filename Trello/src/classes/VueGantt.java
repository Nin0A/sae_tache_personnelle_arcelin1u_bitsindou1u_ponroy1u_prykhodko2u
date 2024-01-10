package classes;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



//нужно сделать так чтобы всегда был отступ в начале иначе не видно стрелки если таши на одной коорд у
public class VueGantt extends Pane implements Observateur {

    private static final int WIDTH = 20;
    private static final int HEIGHT = 30;
    private static final int SUBTASK_PADDING = 10;
    private static final int DAY_SIZE = 30;
    private static final int MONTH_SIZE = 100;

    private Tableau tableau;


    private Map<TacheMere, Double[]> taskPositions = new HashMap<>();


    /**
     * Methode Build Gaaant qui initialise diagramme de Gantt
     */
    private void buildGantt() {
        VBox container = new VBox();
        int nbTaches = 0;
        for (Colonne colonne : tableau.getColonnes()) {
            nbTaches += colonne.getTaches().size();
        }
        if(nbTaches != 0){
            this.setStyle("-fx-background-color: rgb(255,255,255,0.5)");

            LocalDate baseDate = determinerBaseDate(tableau).get(0);
            LocalDate endDate = determinerBaseDate(tableau).get(1);
            long totalDaysLong = ChronoUnit.DAYS.between(baseDate, endDate) + 1;
            int totalDays = Math.toIntExact(totalDaysLong);

            VBox timeLine = createTimeLine(baseDate, totalDays);

            HBox containerCheckBox = new HBox(10); // Ajout de la distance entre les checkboxes (10 pixels)

            for (Colonne colonnetmp : tableau.getColonnes()) {

                for (Tache tache : tableau.getColonneById(colonnetmp.getIdColonne()).getTaches()) {

                    HBox miniCheckBox = new HBox();
                    CheckBox checkBox = new CheckBox(tache.getNom());
                    Label idLabel = new Label(""+tache.idTache);
                    idLabel.setVisible(false);
                    miniCheckBox.getChildren().addAll(checkBox,idLabel);

                    containerCheckBox.getChildren().add(miniCheckBox);
                }
            }

            Button validerButton = new Button("Valider");



            // Style pour le survol
            validerButton.setOnMouseEntered(e -> validerButton.setStyle(




                    "-fx-font-size: 10px; " +
                            "-fx-padding: 5px; " +
                            "-fx-background-color: #fffefe; " + // Nouvelle couleur de fond au survol
                            "-fx-text-fill: #000000; " + // Nouvelle couleur du texte au survol
                            "-fx-border-color: #ffc3f8; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-radius: 50px;" + // Bordure arrondie
                            "-fx-background-radius: 50px;"+
                            "-fx-font-size:  15px;"
            ));

            validerButton.setOnMouseExited(e -> validerButton.setStyle(


                    "-fx-font-size: 10px; " +
                            "-fx-padding: 5px; " +
                            "-fx-background-color: #fffefe; " + // Nouvelle couleur de fond au survol
                            "-fx-text-fill: #000000; " + // Nouvelle couleur du texte au survol
                            "-fx-border-color: #d4ff6a; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-radius: 50px;" + // Bordure arrondie
                            "-fx-background-radius: 50px;"+
                            "-fx-font-size:  15px;"
            ));

            // Associez un gestionnaire d'événements pour le clic sur le bouton "Valider"



            validerButton.setOnAction(event -> {
                ArrayList listeIdTache = new ArrayList<>();

                // Parcourir toutes les HBox dans containerCheckBox lors du clic sur le bouton "Valider"
                for (Node node : containerCheckBox.getChildren()) {


                    if (node instanceof HBox) {
                        HBox miniCheckBoxBox = (HBox) node;
                        for (Node miniNode : miniCheckBoxBox.getChildren()) {
                            if (miniNode instanceof CheckBox) {
                                CheckBox checkBox = (CheckBox) miniNode;
                                if (checkBox.isSelected()) {
                                    // La CheckBox est sélectionnée, vous pouvez faire quelque chose avec elle
                                    String label = checkBox.getText();
                                    HBox hboxtmp = (HBox)checkBox.getParent();
                                    Label l = (Label)hboxtmp.getChildren().get(hboxtmp.getChildren().size()-1);
                                    int idtache = Integer.parseInt(l.getText());
                                    listeIdTache.add(idtache);

                                }
                            }
                        }
                    }
                }


                //clear des taches

                    for(int i=1;i<this.getChildren().size();i++){
                        this.getChildren().remove(i);
                    }


                    for(int i=1;i<this.getChildren().size();i++){
                        this.getChildren().remove(i);
                    }
                    



                //construire le gantt

                double yPos = 100;
                for (Colonne colonne : tableau.getColonnes()) {
                    for (Tache tache : colonne.getTaches()) {
                        if(listeIdTache.contains(tache.idTache)) {
                            yPos = addTaskAndSubtasks(tache, yPos, baseDate, 0);
                        }
                    }
                }

                drawDependencies();


                yPos = 100;
                for (Colonne colonne : tableau.getColonnes()) {
                    for (Tache tache : colonne.getTaches()) {
                        if(listeIdTache.contains(tache.idTache)) {
                            yPos = addTaskAndSubtasks(tache, yPos, baseDate, 0);
                        }
                    }
                }

            });


            containerCheckBox.getChildren().add(validerButton);
            ScrollPane sp = new ScrollPane(containerCheckBox);
            sp.setStyle("-fx-background-color: white");
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            container.getChildren().add(sp);
            container.getChildren().add(timeLine);
            this.getChildren().add(container);

        }

    }

    /**
     * Méthode determinerBaseDate qui détermine la date de début et de fin du projet
     * @param tableau tableau à analyser
     * @return la date de début et de fin du projet
     */
    private  ArrayList<LocalDate> determinerBaseDate(Tableau tableau) {
        LocalDate minDate = LocalDate.MAX;
        LocalDate maxDate = LocalDate.MIN;

        for (Colonne colonne : tableau.getColonnes()) {
            for (Tache tache : colonne.getTaches()) {
                LocalDate startDate = tache.getDateDebut();
                LocalDate endDate = startDate.plusDays((long) tache.getDuree());

                if (startDate.isBefore(minDate)) {
                    minDate = startDate;
                }
                if (endDate.isAfter(maxDate)) {
                    maxDate = endDate;
                }
            }
        }
        maxDate = maxDate.withDayOfMonth(maxDate.getMonth().length(maxDate.isLeapYear()));
        ArrayList<LocalDate> res = new ArrayList<>();
        res.add(minDate);
        res.add(maxDate);
        return res;
    }

    /**
     * Méthode createTimeLine qui crée la ligne de temps
     * @param baseDate date de début du projet
     * @param days  nombre de jours du projet
     * @return la ligne de temps dans un VBox
     */
    private VBox createTimeLine(LocalDate baseDate, int days) {
        HBox daysLine = new HBox();

        HBox monthsLine = new HBox();
        LocalDate currentDate = baseDate;
        for (int day = 0, huita123 = 0; day < days; day++, huita123++) {
            String dayText = String.format("%02d", currentDate.getDayOfMonth());
            Label dayLabel = new Label(dayText);
            dayLabel.setStyle("-fx-padding: 2; -fx-alignment: center;");

            HBox dayContainer = new HBox(dayLabel);
            dayContainer.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-alignment: center;");
            dayContainer.setPrefSize(DAY_SIZE, 20);

            VBox dayBox = new VBox(dayContainer);
            daysLine.getChildren().add(dayBox);

            if (currentDate.getDayOfMonth() == 1) {//replace by method
                Label monthLabel = new Label(String.format("%-12s", currentDate.getMonth()));
                monthLabel.setStyle("-fx-padding: 3; -fx-alignment: center;");


                HBox monthContainer = new HBox(monthLabel);
                monthContainer.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-alignment: center;");
                monthContainer.setPrefSize(MONTH_SIZE, 20);

                monthsLine.getChildren().add(monthContainer);
                HBox.setMargin(monthContainer, new Insets(0,0,0, day > 0 ? (huita123 * DAY_SIZE) - MONTH_SIZE : 0));
                huita123 = 0;
            } else {
                monthsLine.getChildren().add(new Label());
            }

            currentDate = currentDate.plusDays(1);
        }

        VBox timeLine = new VBox();

        timeLine.getChildren().addAll(monthsLine, daysLine);

        return timeLine;
    }

    /**
     * Méthode createTask qui crée une tache
     * @param tache tache à créer
     * @param yPos position en y de la tache
     * @param baseDate date de début du projet
     * @param col couleur de la tache
     * @return la tache dans un Pane
     */
    private Pane createTask(Tache tache, double yPos, LocalDate baseDate, Color col) {
        LocalDate startDate = tache.getDateDebut();
        long startOffset = ChronoUnit.DAYS.between(baseDate, startDate);
        double duration = tache.getDuree();

        Rectangle rect = new Rectangle(startOffset * DAY_SIZE, yPos, duration * DAY_SIZE, HEIGHT);
        rect.setFill(col);
        rect.setStroke(Color.BLACK);

        Label label = new Label(tache.getNom());
        label.setLayoutX(rect.getX() + 5);
        label.setLayoutY(rect.getY() + 5);
        label.setStyle("-fx-text-fill: white;");


        Pane taskPane = new     Pane();
        taskPane.getChildren().addAll(rect, label);

        return taskPane;

    }
    /**
     * Méthode addTaskAndSubtasks qui ajoute une tache et ses sous-taches au diagramme de Gantt
     * @param tache tache à ajouter
     * @param yPos position en y de la tache
     * @param baseDate date de début du projet
     * @param depth profondeur de la tache
     * @return la position en y de la tache
     */
    private double addTaskAndSubtasks(Tache tache, double yPos, LocalDate baseDate, int depth) {

        Color color = (depth == 0) ? Color.rgb(225,142,255) : Color.rgb(255,142,188);
        if (depth==0){
            LocalDate startDate = tache.getDateDebut();
            double startOffset = ChronoUnit.DAYS.between(baseDate, startDate);
            taskPositions.put((TacheMere) tache, new Double[]{startOffset * DAY_SIZE, yPos});
        }

        Pane taskPane = createTask(tache, yPos, baseDate, color);
        this.getChildren().add(taskPane);

        double maxYPos = yPos + WIDTH + SUBTASK_PADDING;

        if (tache instanceof TacheMere) {
            TacheMere tacheMere = (TacheMere) tache;
            double subtaskYPos = yPos + WIDTH + SUBTASK_PADDING;

            for (Tache sousTache : tacheMere.getSousTaches()) {
                double returnedYPos = addTaskAndSubtasks(sousTache, subtaskYPos, baseDate, depth + 1);
                if (returnedYPos > maxYPos) {
                    maxYPos = returnedYPos;
                }
            }
        }

        return maxYPos;
    }

    /**
     * Méthode drawArrowLine qui dessine une flèche entre deux taches
     * @param startX position en x de la tache de départ
     * @param startY position en y de la tache de départ
     * @param endX position en x de la tache d'arrivée
     * @param endY position en y de la tache d'arrivée
     * @param pane pane sur lequel dessiner la flèche
     */
    private void drawArrowLine(double startX, double startY, double endX, double endY, Pane pane) {
        double controlOffsetY = 60;

        double controlX = startX;
        double controlY = startY + controlOffsetY;

        // get the slope of the line and find its angle
        double slope = (startY - endY) / (startX - endX);
        double lineAngle = Math.atan(slope);

        double arrowAngle = startX > endX ? Math.toRadians(45) : -Math.toRadians(225);

        QuadCurve quadCurve = new QuadCurve();
        quadCurve.setStartX(startX);
        quadCurve.setStartY(startY);
        quadCurve.setControlX(controlX);
        quadCurve.setControlY(controlY);
        quadCurve.setEndX(endX);
        quadCurve.setEndY(endY+(HEIGHT/2));

        quadCurve.setStroke(Color.BLACK);
        quadCurve.setStrokeWidth(2);
        quadCurve.setFill(null);

        double arrowLength =  15;

        // create the arrow legs
        Line arrow1 = new Line();
        arrow1.setStartX(quadCurve.getEndX());
        arrow1.setStartY(quadCurve.getEndY());
        arrow1.setEndX(quadCurve.getEndX() + arrowLength * Math.cos(lineAngle - arrowAngle));
        arrow1.setEndY(quadCurve.getEndY() + arrowLength * Math.sin(lineAngle - arrowAngle));

        Line arrow2 = new Line();
        arrow2.setStartX(quadCurve.getEndX());
        arrow2.setStartY(quadCurve.getEndY());
        arrow2.setEndX(quadCurve.getEndX() + arrowLength * Math.cos(lineAngle + arrowAngle));
        arrow2.setEndY(quadCurve.getEndY() + arrowLength * Math.sin(lineAngle + arrowAngle));

        pane.getChildren().addAll(quadCurve, arrow1, arrow2);
    }

    /**
     * Méthode drawDependencies qui dessine les dépendances entre les taches
     */
    private void drawDependencies(){
        for (Tache to : taskPositions.keySet()){
            for (Tache from : taskPositions.keySet()){
                if (to.etreAntecedent(from)){
                    drawArrowLine(taskPositions.get(from)[0], taskPositions.get(from)[1],taskPositions.get(to)[0], taskPositions.get(to)[1],this);
                }
            }
        }
    }
    /**
     * Méthode actualiser qui actualise le diagramme de Gantt
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        Tableau tableau = (Tableau) sujet;
        this.tableau = tableau;
        buildGantt();
    }
}
