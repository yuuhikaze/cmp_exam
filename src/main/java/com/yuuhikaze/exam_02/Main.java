package com.yuuhikaze.exam_02;

import com.yuuhikaze.exam_02.utils.DirManagerSingleton;
import com.yuuhikaze.exam_02.utils.FXMLCore;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        DirManagerSingleton.createDataDir();
        scene = new Scene(FXMLCore.loadFXML("Scene01"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(FXMLCore.loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }
}
