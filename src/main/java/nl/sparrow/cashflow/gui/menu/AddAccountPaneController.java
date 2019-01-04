package nl.sparrow.cashflow.gui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.gui.Controller;

public class AddAccountPaneController extends Controller {

    @FXML
    private TextField inputAccountIban;

    public void addAccount(ActionEvent actionEvent) {
        String iban = inputAccountIban.getText().toUpperCase();

        CashFlowApp.getAccountService().addAccount(iban);
        inputAccountIban.setText("");
    }


}
