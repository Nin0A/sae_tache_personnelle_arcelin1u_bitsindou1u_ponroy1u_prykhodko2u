package test;

import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

public class DraggableColumn extends VBox {
    public DraggableColumn(String title) {
        super(10); // Расстояние между элементами
        setStyle("-fx-border-color: blue; -fx-padding: 10;");

        Label titleLabel = new Label(title);
        getChildren().add(titleLabel);

        setId(title);

        setOnDragDetected(event -> {
            Dragboard db = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(title); // Используем заголовок в качестве идентификатора
            db.setContent(content);
            event.consume();
            System.out.println(1111111);
        });


        setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                // Здесь реализация перемещения колонки
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
            System.out.println(3333);
        });

        setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
            }
            System.out.println(76777);
            event.consume();
        });
//        setOnDragDone(DragEvent::consume);
    }
}
