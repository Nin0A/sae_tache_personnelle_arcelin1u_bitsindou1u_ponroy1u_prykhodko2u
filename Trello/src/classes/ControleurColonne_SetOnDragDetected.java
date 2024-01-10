package classes;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControleurColonne_SetOnDragDetected implements Controleur<MouseEvent> {

   private VBox col;
   private HBox root;

    /**
     * Constructeur de la classe ControleurColonne_SetOnDragDetected
     * @param c colonne sur laquelle on fait un drag
     * @param r racine de la vue
     */
    ControleurColonne_SetOnDragDetected(VBox c, HBox r){
        col = c;
        root = r;
    }
    /**
     * Méthode handle qui gère les actions sur les boutons
     * @param event action sur un bouton
     */
    @Override
    public void handle(MouseEvent event) {
        Dragboard db = col.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(col.getId());
        db.setContent(content);
        event.consume();


        for (Node node : root.getChildren()) {
            if (node instanceof VBox && "placeholderColonne".equals(node.getId())) {
               node.setVisible(true);
            }
        }
    }

}
