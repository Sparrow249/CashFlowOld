package nl.sparrow.gui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nl.sparrow.gui.CashFlowApp;
import nl.sparrow.gui.Controller;
import nl.sparrow.logic.services.AccountService;

public class AddAccountPaneController extends Controller {

    @FXML
    private TextField inputAccountIban;

    public void addAccount(ActionEvent actionEvent) {
        String iban = inputAccountIban.getText().toUpperCase();

        CashFlowApp.getAccountService().addAccount(iban);
        inputAccountIban.setText("");
    }


}
