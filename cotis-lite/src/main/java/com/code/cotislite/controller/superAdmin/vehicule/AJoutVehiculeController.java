package com.code.cotislite.controller.superAdmin.vehicule;

import com.code.cotislite.InterfaceManager;
import com.code.cotislite.database.Database;
import com.code.cotislite.model.Vehicule;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AJoutVehiculeController {
    @FXML
    private TextField numeroVehiculeField;
    @FXML
    private TextField marqueVehiculeField;
    @FXML
    private TextField nbPlaceVehiculeField;

    public void insertVehiculeButtonAction(MouseEvent event) {
        String numeroVehicule = numeroVehiculeField.getText();
        String marqueVehicule = marqueVehiculeField.getText();
        Integer  nbPlaceVehicule = Integer.parseInt(nbPlaceVehiculeField.getText());
        Integer  placeLeft = Integer.parseInt(nbPlaceVehiculeField.getText());

        if (numeroVehicule.isEmpty() || marqueVehicule.isEmpty() || nbPlaceVehicule.toString().isEmpty()) {
            System.out.println("Veuillez remplir tous les champs");
            return;
        }

        if (nbPlaceVehicule < 20) {
            System.out.println("Le nombre de places doit être supérieur à 20");
            return;
        }

        Vehicule vehicule = new Vehicule(numeroVehicule,marqueVehicule,nbPlaceVehicule,placeLeft);
        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Vehicule (NumeroVehicule, MarqueVehicule, NbPlaceVehicule,PlaceLeftVehicule) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, vehicule.getNumeroVehicule());
            preparedStatement.setString(2, vehicule.getMarqueVehicule());
            preparedStatement.setInt(3, vehicule.getNbPlaceVehicule());
            preparedStatement.setInt(4, vehicule.getPlaceLeftVehicule());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void closeAjoutVehiculeAction(MouseEvent event) {
        Button buttonClose = (Button) event.getSource();
        buttonClose.getScene().getWindow().hide();
    }

}
