module org.example.trello {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.trello to javafx.fxml;
    exports org.example.trello;
}