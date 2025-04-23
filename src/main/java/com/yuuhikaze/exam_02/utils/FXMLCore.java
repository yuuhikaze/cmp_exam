package com.yuuhikaze.exam_02.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class FXMLCore {

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ResourceLocator.locateResource("scenes/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static FXMLLoader createLoader(String fxml) throws IOException {
        return new FXMLLoader(ResourceLocator.locateResource("scenes/" + fxml + ".fxml"));
    }
}
