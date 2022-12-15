package com.example.rpg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("utils/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        String musicFileMain = "src/sdtrack.mp3";
        Media soundMain = new Media(new File(musicFileMain).toURI().toString());
        MediaPlayer mediaPlayerMain = new MediaPlayer(soundMain);
        mediaPlayerMain.play();
        launch();

    }
}