package classes;

import javafx.event.EventHandler;

import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class ControleurTache_SetOnDragDetected implements Controleur<MouseEvent> {
    private Node tache;
    private HBox root;
    public ControleurTache_SetOnDragDetected(Node t, HBox r) {
        tache = t;
        root = r;
    }

    @Override
    public void handle(MouseEvent event) {
        Dragboard db = tache.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(tache.getId());
        db.setContent(content);
        event.consume();


        for (Node col : root.getChildren()) {
            for (Node node : ((Pane) col).getChildren()) {
                if ("placeholderTache".equals(node.getId())) {
                    node.setVisible(true);
                }
            }
        }
    }
}