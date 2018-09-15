package main.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import main.models.Account;
import main.services.AccountService;

import java.io.IOException;

public class AccountMenuController extends Controller{

    @FXML
    private Accordion menu;

    private AddAccountPaneController addAccountPaneController;

    public void update(){
        createFxml();
    }

    private void createFxml(){
        //TODO: Only create/remove/update whats needed, not full remake every time
        //TODO: Observer?
        System.out.println("Creating Menu");
        menu.getPanes().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/addAccountPane.fxml"));
        try {
            TitledPane addAccountPane = fxmlLoader.load();
            addAccountPaneController = fxmlLoader.getController();
            addAccountPaneController.setParent(parent);
            menu.getPanes().add(addAccountPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Account account: parent.getAccountService().getAllAccounts()){
            TitledPane titledPane = new TitledPane();
            titledPane.setText(account.getIban());
            System.out.println(account.getIban());
            menu.getPanes().add(titledPane);
        }
    }

}
