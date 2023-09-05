package com.code.cotislite.controller.superAdmin.admin;

import com.code.cotislite.database.Database;
import com.code.cotislite.model.Administrateur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjoutAdminController {

    @FXML
    private TextField pseudoAdminField;
    @FXML
    private TextField contactAdminField;
    @FXML
    private PasswordField passwordAdminField;

    public void insertAdminButtonAction() {
        String pseudoAdmin = pseudoAdminField.getText();
        String passwordAdmin = passwordAdminField.getText();
        String contactAdmin = contactAdminField.getText();

        if (pseudoAdmin.isEmpty() || passwordAdmin.isEmpty() || contactAdmin.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs");
            return;
        }
        Administrateur admin = new Administrateur(pseudoAdmin, passwordAdmin, contactAdmin);

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Admin (PseudoAdmin, PasswordAdmin, ContactAdmin) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, admin.getPseudoAdmin());
            preparedStatement.setString(2, admin.getPasswordAdmin());
            preparedStatement.setString(3, admin.getContactAdmin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void closeAjoutAminViewButtonAction(MouseEvent event) {
        Button buttonClose = (Button) event.getSource();
        buttonClose.getScene().getWindow().hide();

    }
}
