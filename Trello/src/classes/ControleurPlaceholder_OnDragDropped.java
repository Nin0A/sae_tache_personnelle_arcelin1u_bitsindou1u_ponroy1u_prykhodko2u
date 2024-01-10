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
    /**
     * Constructeur de la classe ControleurPlaceholder_OnDragDropped
     * @param r racine de la vue
     * @param pl placeholder
     * @param t tableau
     */
    ControleurPlaceholder_OnDragDropped(VueBureau r, VBox pl, Tableau t){
        root = r;
        placeholder = pl;
        tab = t;
    }
    /**
     * Méthode handle qui gère les actions sur les boutons
     * @param event action sur un bouton
     */
    @Override
    public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasString()) {
            String nodeId = db.getString();
            VBox column = findColumnById(root, nodeId);
            int placeholderIndex = root.getChildren().indexOf(placeholder);
            if (placeholderIndex+1 != root.getChildren().indexOf(column) && placeholderIndex-1 != root.getChildren().indexOf(column)){
                ArrayList<Colonne> liste =  tab.getColonnes();

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

    /**
     * Méthode findColumnById qui trouve une colonne (node) par son id
     * @param root
     * @param id
     * @return
     */
    private VBox findColumnById(HBox root, String id) {
        for (Node node : root.getChildren()) {
            if (node.getId() != null &&  node.getId().equals(id)) {
                return (VBox) node;
            }
        }
        return null;
    }
}
