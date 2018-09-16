package main.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.models.Account;
import main.services.AccountService;

import java.io.IOException;

public class CashFlowApp extends Application {
    private static Stage mainStage;
    private static AccountService accountService;
    private static Account selectedAccount;


    public static AccountService getAccountService() {
        return accountService;
    }

    public static Account getSelectedAccount() {
        return selectedAccount;
    }

    public static void setSelectedAccount(Account selectedAccount) {
        CashFlowApp.selectedAccount = selectedAccount;
    }

    public static void main(String[] args) {
        accountService = new AccountService();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        mainStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cashFlowApp.fxml"));
        Parent root = fxmlLoader.load();

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("CashFlow");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

        //TODO: change window titlebar layout?
    }

}
