package com.code.cotislite;

import com.code.cotislite.database.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class IndexController {
    @FXML
    private TextField pseudoField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button buttonConnexion;
    @FXML
    private AnchorPane anchorpanePrimary;


    public void buttonConnexionAction() throws IOException {
        String pseudoText = pseudoField.getText();
        String passwordText = passwordField.getText();

        try (Connection connection = Database.getConnection()) {
            String query = "SELECT * FROM SuperAdmin WHERE PseudoSuperAdmin = ? AND PasswordSuperAdmin= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pseudoText);
            preparedStatement.setString(2, passwordText);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                openSuperAdminInterface();
            } else {
                System.out.println("Echec de la connexion");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void openSuperAdminInterface() throws IOException {
        Parent superAdminInterface = FXMLLoader.load(getClass().getResource("superAdmin/superAdmin-view.fxml"));
        Scene superAdminVue = new Scene(superAdminInterface);
        Stage primaryStage = (Stage) buttonConnexion.getScene().getWindow();
        primaryStage.setScene(superAdminVue);
        primaryStage.show();

    }
    private void openSuperAdminLoginInterface() {
        try {

            FXMLLoader superAdminLoginInterface = new FXMLLoader();
            superAdminLoginInterface.setLocation(IndexController.class.getResource("index-viewSuperAdmin.fxml"));
            anchorpanePrimary.getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(superAdminLoginInterface.load(), 750, 450);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void openAdminLoginInterface(AnchorPane anchorPane){
        try {

            FXMLLoader adminInterface = new FXMLLoader();
            adminInterface.setLocation(IndexController.class.getResource("index-viewAdmin.fxml"));
            AnchorPane anchorPaneAdmin = adminInterface.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(anchorPaneAdmin);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void buttonLoginSuperAdminAction(MouseEvent event) throws IOException {
        openSuperAdminLoginInterface();
    }


    public void buttonLoginAdminAction(MouseEvent event) {
        openAdminLoginInterface(anchorpanePrimary);
    }




}
//Manao interface hafa
//Interface Admin pour se communique