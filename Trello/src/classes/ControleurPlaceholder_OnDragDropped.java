package classes;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ControleurPlaceholder_OnDragDropped implements Controleur<DragEvent> {


    private VueBureau root;
    private VBox placeholder;
    private Tableau tab;

    ControleurPlaceholder_OnDragDropped(VueBureau r, VBox pl, Tableau t){
        root = r;
        placeholder = pl;
        tab = t;
    }

    @Override
    public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasString()) {
            String nodeId = db.getString();
            VBox column = findColumnById(root, nodeId);
            int placeholderIndex = root.getChildren().indexOf(placeholder);
            if (placeholderIndex+1 != root.getChildren().indexOf(column) && placeholderIndex-1 != root.getChildren().indexOf(column)){
                ArrayList<Colonne> liste =  tab.getColonnes();
                System.out.println("1111111111111111111111111");
                int fromIndex = Integer.parseInt(nodeId);
                int toIndex = (root.getChildren().indexOf((VBox)event.getGestureTarget())) <= fromIndex
                        ? (root.getChildren().indexOf((VBox)event.getGestureTarget())) / 2
                        : (root.getChildren().indexOf((VBox)event.getGestureTarget()) - 2) / 2;
                if (fromIndex < liste.size() && toIndex < liste.size()) {

                    Colonne elementToMove = liste.remove(fromIndex);
                    liste.add(toIndex, elementToMove);

                }

                event.setDropCompleted(true);
            }
        }
        event.consume();
        root.actualiser(tab);
    }

    private VBox findColumnById(HBox root, String id) {
        for (Node node : root.getChildren()) {
            if (node.getId() != null &&  node.getId().equals(id)) {
                return (VBox) node;
            }
        }
        return null;
    }
}
