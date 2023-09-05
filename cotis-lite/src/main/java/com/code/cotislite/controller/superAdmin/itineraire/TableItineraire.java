package com.code.cotislite.controller.superAdmin.itineraire;

import com.code.cotislite.InterfaceManager;
import com.code.cotislite.database.Database;
import com.code.cotislite.model.Itineraire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TableItineraire implements Initializable {

    public TextField filedRechercher;
    @FXML
    private TableView<Itineraire> itineraireTable;
    @FXML
    private TableColumn<Itineraire, String> codeCol;
    @FXML
    private TableColumn<Itineraire, String> nomCol;
    @FXML
    private TableColumn<Itineraire, String> distanceCol;
    @FXML
    private TableColumn<Itineraire, String> prixCol;

    ObservableList<Itineraire> itineraireList = FXCollections.observableArrayList();

    private static String codeItineraire,nomItineraire,distanceItineraire,prixItineraire;

    public static String getCodeItineraire() {
        return codeItineraire;
    }

    public static void setCodeItineraire(String codeItineraire) {
        TableItineraire.codeItineraire = codeItineraire;
    }

    public static String getNomItineraire() {
        return nomItineraire;
    }

    public static void setNomItineraire(String nomItineraire) {
        TableItineraire.nomItineraire = nomItineraire;
    }

    public static String getDistanceItineraire() {
        return distanceItineraire;
    }

    public static void setDistanceItineraire(String distanceItineraire) {
        TableItineraire.distanceItineraire = distanceItineraire;
    }

    public static String getPrixItineraire() {
        return prixItineraire;
    }

    public static void setPrixItineraire(String prixItineraire) {
        TableItineraire.prixItineraire = prixItineraire;
    }



    public void loadData() throws SQLException {
        // Associer les colonnes du tableau aux propriétés de l'objet Administrateur

        codeCol.setCellValueFactory(new PropertyValueFactory<>("CodeItineraire"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("NomItineraire"));
        distanceCol.setCellValueFactory(new PropertyValueFactory<>("DistanceItineraire"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("PrixItineraire"));

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Itineraire");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            itineraireList.clear();

            while (resultSet.next()) {
                Itineraire itineraire = new Itineraire(
                        resultSet.getString("CodeItineraire"),
                        resultSet.getString("NomItineraire"),
                        resultSet.getInt("DistanceItineraire"),
                        resultSet.getFloat("PrixItineraire")
                );
                itineraireList.add(itineraire);
            }  // Mettre à jour les données affichées dans la table
            itineraireTable.setItems(itineraireList);

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



    public void buttonAjoutItineraireAction(MouseEvent event) throws IOException {
        InterfaceManager.openInterface("superAdmin/itineraire/ajoutItineraire-view.fxml", "Ajouter Itinéraire");

    }


    public void bouttonModifierItineraireAction(MouseEvent event) throws IOException {
        if (itineraireTable.getSelectionModel().getSelectedIndex() != -1) {
            Itineraire itineraire = itineraireTable.getSelectionModel().getSelectedItem();
            setCodeItineraire(itineraire.getCodeItineraire());
            setNomItineraire(itineraire.getNomItineraire());
            setDistanceItineraire(String.valueOf(itineraire.getDistanceItineraire()));
            setPrixItineraire(String.valueOf(itineraire.getPrixItineraire()));
            InterfaceManager.openInterface("superAdmin/itineraire/modifierItineraire-view.fxml", "Ajouter Itinéraire");

        }
    }

    public void bouttonSupprimerItineraire(MouseEvent event) throws SQLException {
        if (itineraireTable.getSelectionModel().getSelectedIndex() != -1) {
            Itineraire itineraire = itineraireTable.getSelectionModel().getSelectedItem();
            try(Connection connection = Database.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM Itineraire WHERE  CodeItineraire = ?")) {
                preparedStatement.setString(1,itineraire.getCodeItineraire());
                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            loadData();
        }
    }

    @FXML
    public void bouttonRechercherAction(ActionEvent actionEvent) {
        String recherche = filedRechercher.getText().trim();
        if (!recherche.isEmpty()) {

            String sql = "SELECT * FROM Itineraire WHERE " +
                    "CodeItineraire LIKE ? OR " +
                    "NomItineraire LIKE ? OR " +
                    "DistanceItineraire LIKE ? OR " +
                    "PrixItineraire LIKE ?";
            try (Connection connection = Database.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                String rechercheParam = "%" + recherche + "%";
                for (int i = 1; i <= 4; i++) {
                    preparedStatement.setString(i, rechercheParam);
                }

                ResultSet resultSet = preparedStatement.executeQuery();

                // Créez une liste pour stocker les résultats de la recherche
                List<Itineraire> resultats = new ArrayList<>();
                while (resultSet.next()) {
                    String codeItineraire = resultSet.getString("CodeItineraire");
                    String nomItineraire = resultSet.getString("NomItineraire");
                    int distanceItineraire = resultSet.getInt("DistanceItineraire");
                    float prixItineraire = resultSet.getFloat("PrixItineraire");

                    // Créez un objet Itineraire avec les données de la base de données
                    Itineraire itineraire = new Itineraire(codeItineraire, nomItineraire, distanceItineraire, prixItineraire);
                    resultats.add(itineraire);
                }

                // Mettez à jour le TableView avec les résultats de la recherche
                itineraireTable.getItems().clear(); // Effacez les données existantes
                itineraireTable.getItems().addAll(resultats); // Ajoutez les résultats de la recherche

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de recherche");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer un terme de recherche.");
            alert.showAndWait();
        }
    }


}
