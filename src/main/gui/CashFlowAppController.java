package main.gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.gui.menu.AccountMenuController;
import main.gui.toolBar.ToolBarController;
import main.models.Account;
import main.services.AccountService;

import java.io.IOException;

public class CashFlowAppController extends Controller {
    private AccountService accountService;
    private Account selectedAccount;

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

    public void initialize() {

        toolBarController.setParent(this);
        accountMenuController.setParent(this);
        switchScene(View.CSV_UPLOAD_VIEW.toString());
    }

    public void setAccountService(AccountService accountService) {
        System.out.println("Setting AS to CashFlowAppController: " + accountService);
        this.accountService = accountService;
        refresh();
    }

    @Override
    public void switchScene(String fxml) {
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

    @Override
    public Account getSelectedAccount() {
        return selectedAccount;
    }

    @Override
    public AccountService getAccountService() {
        return accountService;
    }

    public void refresh() {
        accountMenuController.refresh();
        contentController.refresh();
    }
}
