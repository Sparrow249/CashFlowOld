package main.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.gui.controllers.CashFlowAppController;
import main.services.AccountService;

import java.io.IOException;

public class CashFlowApp extends Application {
    private static Stage mainStage;
    private static AccountService accountService;

    public static void main(String[] args) {
        accountService = new AccountService();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/cashFlowApp.fxml"));
        Parent root = fxmlLoader.load();
        CashFlowAppController controller = fxmlLoader.getController();
        controller.setAccountService(accountService);

        primaryStage.setTitle("CashFlow");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

        //TODO: change window titlebar layout?
    }

}
