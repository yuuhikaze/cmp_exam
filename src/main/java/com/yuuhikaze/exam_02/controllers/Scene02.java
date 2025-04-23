package com.yuuhikaze.exam_02.controllers;

import com.yuuhikaze.exam_02.ConnectionSingleton;
import com.yuuhikaze.exam_02.Person;
import com.yuuhikaze.exam_02.model.MySQLConnection;
import com.yuuhikaze.exam_02.utils.FXMLCore;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class Scene02 implements AutoCloseable {
    private MySQLConnection connection = ConnectionSingleton.connection;
    @FXML
    private TableColumn<Person, String> nameCol;
    @FXML
    private TableColumn<Person, String> surnameCol;
    @FXML
    private TableColumn<Person, Integer> careerCol;
    @FXML
    private Button addButton;
    @FXML
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Person> tableView;

    private ObservableList<Person> persons;

    @FXML
    public void initialize() throws SQLException {
        persons = FXCollections.observableArrayList();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        careerCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        connection.executeQuery("SELECT * FROM students");
        var res = connection.getResultSet();

        while (res.next()) {
            String name = res.getString("name");
            String surname = res.getString("surname");
            String career = res.getString("career");

            Person p = new Person(name, surname, career);
            persons.add(p);
        }

        tableView.setItems(persons);
    }

    @FXML
    public void addPerson(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader03 = FXMLCore.createLoader("Scene03");
        Scene scene03 = new Scene(loader03.load());
        Scene03 controller03 = loader03.getController();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene03);
        stage.showAndWait();

        Person person;
        try {
            person = controller03.getPerson();
        } catch (NullPointerException e) {
            return;
        }

        if (!persons.contains(person)) {
            connection.executeInstruction(
                "INSERT INTO students (name,surname,career) VALUES (\"" + person.getName() + "\",\"" + person.getSurname() + "\"," + person.getCareer() + ")");
            persons.add(person);
            tableView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Person already exists");
            alert.showAndWait();
        }
    }

    @FXML
    public void deletePerson(ActionEvent event) throws SQLException {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            connection.executeInstruction(
                "DELETE FROM students WHERE name = \"" + selectedPerson.getName() + "\" AND surname = \"" + selectedPerson.getSurname() + "\" AND career = " + selectedPerson.getCareer());
            persons.remove(selectedPerson);
            tableView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Warning");
            alert.setContentText("No person selected to delete");
            alert.showAndWait();
        }
    }

    @Override
    public void close() throws Exception {
        connection.closeConnection();
    }
}
