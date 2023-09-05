module com.code.cotislite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.code.cotislite to javafx.fxml;
    exports com.code.cotislite;
    exports com.code.cotislite.controller.superAdmin.itineraire;
    exports com.code.cotislite.controller.superAdmin;
    exports com.code.cotislite.controller.superAdmin.admin to javafx.fxml;
    opens com.code.cotislite.controller.superAdmin to javafx.fxml;
    opens com.code.cotislite.controller.superAdmin.admin to javafx.fxml;
    opens com.code.cotislite.model to javafx.base;
    opens com.code.cotislite.controller.superAdmin.itineraire;
    exports com.code.cotislite.controller.superAdmin.vehicule;
    opens com.code.cotislite.controller.superAdmin.vehicule;
    exports com.code.cotislite.controller.admin to javafx.fxml;
    exports com.code.cotislite.controller.superAdmin.chat to javafx.fxml;
}