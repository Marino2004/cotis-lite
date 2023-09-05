package com.code.cotislite.controller.superAdmin;

import com.code.cotislite.IndexController;
import com.code.cotislite.database.Database;
import com.code.cotislite.InterfaceManager;
import com.code.cotislite.model.Administrateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SuperAdminController implements Initializable {
    @FXML
    private TableView<Administrateur> adminTable;
    @FXML
    private TableColumn<Administrateur, Integer> idCol;
    @FXML
    private TableColumn<Administrateur, String> pseudoCol;
    @FXML
    private TableColumn<Administrateur, String> passwordCol;
    @FXML
    private TableColumn<Administrateur, String> contactCol;
    @FXML
    private AnchorPane anchorPaneSuperAdmin;
    @FXML
    private AnchorPane anchorPaneSuperAdminInitial;


    ObservableList<Administrateur> AdministrateurList = FXCollections.observableArrayList();


    // 1 -->Partie Administrateur qui est ouvert par défaut :
    public void ajouterAdminButtonAction() throws IOException {
        ajouterAdminInterface();
    }

    private void ajouterAdminInterface() throws IOException {
        InterfaceManager.openInterface("superAdmin/admin/ajoutAdmin-view.fxml", "Ajouter Admin");
    }

    public void loadData() throws SQLException {
        // Associer les colonnes du tableau aux propriétés de l'objet Administrateur

        idCol.setCellValueFactory(new PropertyValueFactory<>("idAdmin"));
        pseudoCol.setCellValueFactory(new PropertyValueFactory<>("pseudoAdmin"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("passwordAdmin"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactAdmin"));


        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Admin");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            AdministrateurList.clear();

            while (resultSet.next()) {
                Administrateur admin = new Administrateur(
                        resultSet.getInt("idAdmin"),
                        resultSet.getString("PseudoAdmin"),
                        resultSet.getString("PasswordAdmin"),
                        resultSet.getString("ContactAdmin")
                );
                AdministrateurList.add(admin);
            }  // Mettre à jour les données affichées dans la table
            adminTable.setItems(AdministrateurList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void buttonAdministrateurAction (MouseEvent event) throws IOException {
        openButtonAdministrateur(anchorPaneSuperAdmin);

    }
    @FXML
    private void buttonVehiculeAction(MouseEvent event) throws IOException {
        openButtonVehicule(anchorPaneSuperAdmin);

    }
    @FXML
    private void buttonItineraireAction (MouseEvent event) throws IOException {
        openButtonItineraire(anchorPaneSuperAdmin);
    }
    @FXML
    private void buttonChatAction(MouseEvent event) throws IOException {
        openButtonChat(anchorPaneSuperAdmin);
    }
    @FXML
    public void buttonLogOutAction(MouseEvent event) throws IOException {
        openButtonLogOut(anchorPaneSuperAdmin);
    }

    private void openButtonAdministrateur(AnchorPane anchorPane) throws IOException {
        try {

            FXMLLoader adminInterface = new FXMLLoader();
            adminInterface.setLocation(IndexController.class.getResource("superAdmin/superAdmin-view.fxml"));
            anchorPaneSuperAdmin.getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(adminInterface.load(), 750, 450);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void openButtonVehicule(AnchorPane anchorPane) throws IOException {
        try {

            FXMLLoader adminInterface = new FXMLLoader();
            adminInterface.setLocation(IndexController.class.getResource("superAdmin/vehicule/vehicule-view.fxml"));
            AnchorPane anchorPaneAdmin = adminInterface.load();
            anchorPaneSuperAdmin.getChildren().clear();
            anchorPaneSuperAdmin.getChildren().add(anchorPaneAdmin);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void openButtonItineraire(AnchorPane anchorPane) throws IOException {
        try {

            FXMLLoader adminInterface = new FXMLLoader();
            adminInterface.setLocation(IndexController.class.getResource("superAdmin/itineraire/itineraire-view.fxml"));
            AnchorPane anchorPaneAdmin = adminInterface.load();
            anchorPaneSuperAdmin.getChildren().clear();
            anchorPaneSuperAdmin.getChildren().add(anchorPaneAdmin);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void openButtonChat(AnchorPane anchorPane) {
        try {

            FXMLLoader adminInterface = new FXMLLoader();
            adminInterface.setLocation(IndexController.class.getResource("superAdmin/chat/chat-view.fxml"));
            AnchorPane anchorPaneAdmin = adminInterface.load();
            anchorPaneSuperAdmin.getChildren().clear();
            anchorPaneSuperAdmin.getChildren().add(anchorPaneAdmin);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void openButtonLogOut(AnchorPane anchorPane) throws IOException {
        try {

            FXMLLoader adminInterface = new FXMLLoader();
            adminInterface.setLocation(IndexController.class.getResource("index-viewSuperAdmin.fxml"));
            anchorPaneSuperAdmin.getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(adminInterface.load(), 750, 450);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
