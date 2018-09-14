package main.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CashFlowApp extends Application {
    private static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("cashFlowApp.fxml"));

        primaryStage.setTitle("CashFlow");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

        //TODO: change window titlebar layout?
    }

    public static Stage getMainStage() {
        return mainStage;
    }
}
