package classes;
import javafx.geometry.Insets;
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



    private void buildGantt() {

        this.getChildren().clear();
        this.setStyle("-fx-background-color: rgb(255,255,255,0.5)");

        LocalDate baseDate = determinerBaseDate(tableau).get(0);
        LocalDate endDate = determinerBaseDate(tableau).get(1);
        long totalDaysLong = ChronoUnit.DAYS.between(baseDate, endDate) + 1;
        int totalDays = Math.toIntExact(totalDaysLong);

        VBox timeLine = createTimeLine(baseDate, totalDays);
        this.getChildren().add(timeLine);

        double yPos = 100;
        for (Colonne colonne : tableau.getColonnes()) {
            for (Tache tache : colonne.getTaches()) {
                yPos = addTaskAndSubtasks(tache, yPos, baseDate, 0);
            }
        }

        drawDependencies();

        yPos = 100;
        for (Colonne colonne : tableau.getColonnes()) {
            for (Tache tache : colonne.getTaches()) {
                yPos = addTaskAndSubtasks(tache, yPos, baseDate, 0);
            }
        }
    }

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


        Pane taskPane = new Pane();
        taskPane.getChildren().addAll(rect, label);

        return taskPane;

    }

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


    private void drawDependencies(){
        for (Tache to : taskPositions.keySet()){
            for (Tache from : taskPositions.keySet()){
                if (to.etreAntecedent(from)){
                    drawArrowLine(taskPositions.get(from)[0], taskPositions.get(from)[1],taskPositions.get(to)[0], taskPositions.get(to)[1],this);
                }
            }
        }
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
