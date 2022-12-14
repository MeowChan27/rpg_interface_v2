package com.example.rpg.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class SceneWinController {

    @FXML
    public void toQuit(){
        Platform.exit();
    }
}
