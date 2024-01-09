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


        for (Node col :  root.getChildren()) {
            for (Node node : ((Pane)col).getChildren() ) {
                if ("placeholderTache".equals(node.getId())) {
                    node.setVisible(true);
                } else if (node.getId() != null && node.getId().contains("tache")) {
                    showPlaceholdersOfTasks(node);
                }
            }
        }
    }


    private void showPlaceholdersOfTasks(Node node){
        if (node.getId().contains("tache")) {
            int i = 0;
            for (Node sousTache : ((Pane) node).getChildren()){
                if (i==0) {
                    i++;
                    continue;
                }
                if ("placeholderTache".equals(sousTache.getId())) {
//                    Tache t = (Tache) ((Pane)sousTache.getParent()).getChildren().get(((Pane)sousTache.getParent()).getChildren().indexOf(sousTache)-1).getUserData();
                }
            }
//            node.setVisible(true);
        }
    }
}