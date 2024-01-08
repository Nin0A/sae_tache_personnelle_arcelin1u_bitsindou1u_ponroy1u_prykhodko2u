package classes;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.util.EventObject;

public interface Controleur<Type extends Event> extends EventHandler<Type> {
}
