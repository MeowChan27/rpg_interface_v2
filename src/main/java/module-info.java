module com.example.rpg {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.rpg to javafx.fxml;
    exports com.example.rpg;
    exports com.example.rpg.controllers;
    opens com.example.rpg.controllers to javafx.fxml;
}