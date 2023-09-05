package com.code.cotislite.controller.superAdmin.itineraire;

import com.code.cotislite.database.Database;
import com.code.cotislite.model.Itineraire;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifierItineraireController implements Initializable {
    @FXML
    private TextField codeItiineraireField;
    @FXML
    private TextField nomItiineraireField;
    @FXML
    private TextField distanceItineraireField;
    @FXML
    private TextField prixItineraireField;
    public void updateItineraireButtonAction(MouseEvent event) throws SQLException {
        String codeItineraire = codeItiineraireField.getText();
        String nomItineraire = nomItiineraireField.getText();
        int distanceItineraire = Integer.parseInt(distanceItineraireField.getText());
        float prixItineraie = Float.parseFloat(prixItineraireField.getText());
        Itineraire itineraire = new Itineraire(codeItineraire,nomItineraire,distanceItineraire,prixItineraie);

        try(Connection connection = Database.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Itineraire SET NomItineraire = ?, DistanceItineraire = ?, PrixItineraire = ? WHERE CodeItineraire = ?")) {
            preparedStatement.setString(1, itineraire.getNomItineraire());
            preparedStatement.setInt(2, itineraire.getDistanceItineraire());
            preparedStatement.setFloat(3, itineraire.getPrixItineraire());
            preparedStatement.setString(4, itineraire.getCodeItineraire());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       
        Button buttonClose = (Button) event.getSource();
        buttonClose.getScene().getWindow().hide();
    }

    public void CloseAJoutItineraireAction(MouseEvent event) {
        Button buttonClose = (Button) event.getSource();
        buttonClose.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        codeItiineraireField.setText(TableItineraire.getCodeItineraire());
        nomItiineraireField.setText(TableItineraire.getNomItineraire());
        distanceItineraireField.setText(TableItineraire.getDistanceItineraire());
        prixItineraireField.setText(TableItineraire.getPrixItineraire());
    }
}
