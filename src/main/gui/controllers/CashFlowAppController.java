package main.gui.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.services.AccountService;

import java.io.IOException;

public class CashFlowAppController {
    private AccountService accountService;

    public AccountService getAccountService() {
        return accountService;
    }

    @FXML
    private BorderPane base;

    @FXML
    private AnchorPane topPane;

    @FXML
    private AnchorPane leftPane;


    @FXML
    private ToolBarController toolBarController;

    @FXML
    private AccountMenuController accountMenuController;

    private Controller contentController;


    public Controller getContentController() {
        return contentController;
    }

    public void initialize() {
        toolBarController.setParent(this);
        accountMenuController.setParent(this);
        switchCenterScene("../fxml/csvUploadView.fxml");
    }


    private void switchCenterScene(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        try {
            AnchorPane view = fxmlLoader.load();
            base.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentController = fxmlLoader.getController();
        contentController.setParent(this);

    }

    public void setAccountService(AccountService accountService) {
        System.out.println("Setting AS to CashFlowAppController: "+accountService);
        this.accountService = accountService;
        refresh();
    }

    public void refresh(){
        accountMenuController.update();
    }
}
