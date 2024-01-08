package test;
import classes.SousTache;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox(5); // Расстояние между элементами

        // Добавляем колонки и плейсхолдеры
        for (int i = 0; i < 3; i++) {
            DraggableColumn column = new DraggableColumn("Колонка " + (i + 1));
            root.getChildren().add(column);

            if (i < 2) { // Добавляем плейсхолдеры между колонками
                root.getChildren().add(createPlaceholder(root));
            }
        }

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Пример Drag and Drop с Плейсхолдерами");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createPlaceholder(HBox root) {
        VBox placeholder = new VBox();
        placeholder.setPrefWidth(50); // Увеличиваем ширину плейсхолдера
        placeholder.setStyle("-fx-background-color: grey;"); // Делаем плейсхолдер видимым

        placeholder.setOnDragOver(event -> {
            if (event.getGestureSource() != placeholder &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
            System.out.println(2222);
        });

        placeholder.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                String nodeId = db.getString();
                DraggableColumn column = findColumnById(root, nodeId);
                int placeholderIndex = root.getChildren().indexOf(placeholder);
                root.getChildren().remove(column);
                root.getChildren().add(placeholderIndex, column);
                event.setDropCompleted(true);
            }
            event.consume();
        });

        return placeholder;
    }

    private DraggableColumn findColumnById(HBox root, String id) {
        for (Node node : root.getChildren()) {
            if (node instanceof DraggableColumn && node.getId().equals(id)) {
                return (DraggableColumn) node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
