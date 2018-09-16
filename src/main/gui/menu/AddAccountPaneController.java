package main.gui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.gui.CashFlowApp;
import main.gui.Controller;
import main.services.AccountService;

public class AddAccountPaneController extends Controller {

    @FXML
    private TextField inputAccountIban;

    public void addAccount(ActionEvent actionEvent) {
        String iban = inputAccountIban.getText().toUpperCase();

        CashFlowApp.getAccountService().addAccount(iban);
        inputAccountIban.setText("");
    }


}
