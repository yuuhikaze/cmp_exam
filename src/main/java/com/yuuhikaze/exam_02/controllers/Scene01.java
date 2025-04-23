package com.yuuhikaze.exam_02.controllers;

import com.yuuhikaze.exam_02.utils.ConnectionSingleton;
import com.yuuhikaze.exam_02.model.MySQLConnection;
import com.yuuhikaze.exam_02.utils.FXMLCore;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Scene01 {
    private MySQLConnection connection = ConnectionSingleton.connection;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    public void login(ActionEvent event) {
        try {
            connection.executeQuery(
                    "SELECT user FROM auth WHERE user = \"" + userField.getText() + "\" AND password = \""
                            + passwordField.getText() + "\"");
            var res = connection.getResultSet();
            if (res.isBeforeFirst()) {
                FXMLLoader loader02 = FXMLCore.createLoader("Scene02");
                Scene scene02 = new Scene(loader02.load(), 640, 360);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene02);
                stage.show();
                Stage this_stage = (Stage) this.loginButton.getScene().getWindow();
                this_stage.close();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("User not found in database");
                alert.show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
