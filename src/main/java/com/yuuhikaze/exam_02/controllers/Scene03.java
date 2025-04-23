package com.yuuhikaze.exam_02.controllers;

import com.yuuhikaze.exam_02.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scene03 {

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField careerField;
    @FXML
    private TextField ageField;
    @FXML
    private Button saveButton;
    @FXML
    private Button closePopupButton;

    private Person person;

    public Person getPerson() {
        if (person == null) {
            throw new NullPointerException("Person data is not available");
        }
        return person;
    }

    public void initTextFields(String name, String surname, int age) {
        nameField.setText(name);
        surnameField.setText(surname);
        ageField.setText(String.valueOf(age));
    }

    @FXML
    public void savePerson() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String career = careerField.getText();
        person = new Person(name, surname, career);
        Stage stage = (Stage)this.saveButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void closePopup() {
        person = null;
        Stage stage = (Stage)this.closePopupButton.getScene().getWindow();
        stage.close();
    }
}
