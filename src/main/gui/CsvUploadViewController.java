package main.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.models.Account;
import main.services.RaboCsvTransactionReader;
import main.services.RaboTransactionMapper;

import java.io.File;
import java.util.List;

public class CsvUploadViewController {
    @FXML
    private Button selectCsvFile;

    @FXML
    private TextField inputAccountIban;

    @FXML
    private TextField inputAccountName;



    public void selectCsvFile(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        Stage fileChooserWindow = new Stage();
        File file = fileChooser.showOpenDialog(selectCsvFile.getScene().getWindow());
        if(file != null) {
            RaboCsvTransactionReader reader = new RaboCsvTransactionReader();
            List<String[]> data = reader.readFile(file);
            RaboTransactionMapper mapper = new RaboTransactionMapper(data.remove(0));
            mapper.mapToTransaction(data);
        }
    }

    private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

        try {
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home") + "\\documents")
            );
        }
        catch(RuntimeException exception){
            fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
            );
        }
    }

    public void addAccount(ActionEvent actionEvent) {
        String iban = inputAccountIban.getText().toUpperCase();
        String name = inputAccountName.getText();

        if(isValidIbanRabo(iban)) {
            new Account(iban, name);
            inputAccountIban.setText("");
            inputAccountName.setText("");
        }
        else{
            System.out.println("Iban is not a valid Rabobank Nederland Iban");
        }


        //TODO: update the sidebar to show account
    }

    private boolean isValidIbanRabo(String iban) {
        return iban.matches("^NL[0-9]{2}RABO[0-9]{10}$");
        //return iban.matches("^[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{10}$");
    }
}
