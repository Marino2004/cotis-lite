package com.code.cotislite.controller.superAdmin.vehicule;

import com.code.cotislite.InterfaceManager;
import com.code.cotislite.database.Database;
import com.code.cotislite.model.Itineraire;
import com.code.cotislite.model.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableVehicule implements Initializable {
    @FXML
    private TableView<Vehicule> vehiculeTable;
    @FXML
    private TableColumn<Vehicule, String> idVehiculeCol;
    @FXML
    private TableColumn<Vehicule, String> numeroVehiculeCol;
    @FXML
    private TableColumn<Vehicule, String> modelVehiculeCol;
    @FXML
    private TableColumn<Vehicule, String> nbPlaceVehiculeCol;

    ObservableList<Vehicule> vehiculeList = FXCollections.observableArrayList();

    public void loadData() throws SQLException {
        // Associer les colonnes du tableau aux propriétés de l'objet Administrateur

        idVehiculeCol.setCellValueFactory(new PropertyValueFactory<>("IdVehicule"));
        numeroVehiculeCol.setCellValueFactory(new PropertyValueFactory<>("NumeroVehicule"));
        modelVehiculeCol.setCellValueFactory(new PropertyValueFactory<>("MarqueVehicule"));
        nbPlaceVehiculeCol.setCellValueFactory(new PropertyValueFactory<>("NbPlaceVehicule"));


        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehicule");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            vehiculeList.clear();

            while (resultSet.next()) {
                Vehicule vehicule = new Vehicule(
                        resultSet.getInt("IdVehicule"),
                        resultSet.getString("NumeroVehicule"),
                        resultSet.getString("MarqueVehicule"),
                        resultSet.getInt("NbPlaceVehicule")

                );
                vehiculeList.add(vehicule);
            }  // Mettre à jour les  affichées dans la table
            vehiculeTable.setItems(vehiculeList);

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
        if(vehiculeTable.getSelectionModel().getSelectedIndex()==1)
        {
            System.out.println("tafiditra");
        }
    }


    public void buttonAJouterVehiculeView(MouseEvent event) throws IOException {
        InterfaceManager.openInterface("superAdmin/vehicule/ajoutVehicule-view.fxml","Ajout Vehicule");
    }
}
