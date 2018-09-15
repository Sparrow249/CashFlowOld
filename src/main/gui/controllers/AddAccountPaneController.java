package main.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddAccountPaneController extends Controller{
    
    @FXML
    private TextField inputAccountIban;


    public void addAccount(ActionEvent actionEvent) {
        String iban = inputAccountIban.getText().toUpperCase();

        if(isValidIbanRabo(iban)) {
            parent.getAccountService().createAccount(iban);
            inputAccountIban.setText("");
            parent.refresh();
        }
        else{
            System.out.println("Iban is not a valid Rabobank Nederland Iban");
        }

    }

    private boolean isValidIbanRabo(String iban) {
        return iban.matches("^NL[0-9]{2}RABO[0-9]{10}$");
        //return iban.matches("^[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{10}$");
    }
}
