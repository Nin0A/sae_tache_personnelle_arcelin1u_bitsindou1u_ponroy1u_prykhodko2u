package classes;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControleurColonne_SetOnDragDetected implements Controleur<MouseEvent> {

   private VBox col;
   private HBox root;

    ControleurColonne_SetOnDragDetected(VBox c, HBox r){
        col = c;
        root = r;
    }

    @Override
    public void handle(MouseEvent event) {
        Dragboard db = col.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(col.getId());
        db.setContent(content);
        event.consume();

        System.out.println(1111111);

        for (Node node : root.getChildren()) {
            if (node instanceof VBox && "placeholder".equals(node.getId())) {
               node.setVisible(true);
            }
        }
    }

}
