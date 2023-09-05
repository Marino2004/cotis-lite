package com.code.cotislite;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.function.BiConsumer;

public class InterfaceManager {

    public static void openInterface(String fxmlFilePath, String title) throws IOException {
        openInterface(fxmlFilePath, title, null);
    }

    public static void openInterface(String fxmlFilePath, String title, BiConsumer<Object, Stage> initializeFunction) throws IOException {
        FXMLLoader loader = new FXMLLoader(InterfaceManager.class.getResource(fxmlFilePath));
        Parent interfaceContent = loader.load();

        Scene interfaceScene = new Scene(interfaceContent);
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setX(307);
        primaryStage.setY(93);
        primaryStage.setScene(interfaceScene);
        primaryStage.setTitle(title);
        primaryStage.show();

        if (initializeFunction != null) {
            initializeFunction.accept(loader.getController(), primaryStage);
        }
    }
}
