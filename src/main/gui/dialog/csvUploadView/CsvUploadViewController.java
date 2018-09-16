package main.gui.dialog.csvUploadView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import main.gui.Controller;
import main.gui.View;
import main.services.RaboCsvTransactionReader;
import main.services.RaboTransactionMapper;

import java.io.File;
import java.util.List;

public class CsvUploadViewController extends Controller {

    @FXML
    private Button selectCsvFile;

    public void selectCsvFile(ActionEvent actionEvent) {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

//        Stage fileChooserWindow = new Stage();
        File file = fileChooser.showOpenDialog(selectCsvFile.getScene().getWindow());
        if (file != null) {
            RaboCsvTransactionReader reader = new RaboCsvTransactionReader();
            List<String[]> data = reader.readFile(file);
            RaboTransactionMapper mapper = new RaboTransactionMapper(getAccountService(), data.remove(0));
            mapper.mapToTransaction(data);
        }

        parent.switchScene(View.TRANSACTION_OVERVIEW.toString());
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

        try {
            fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home") + "\\documents")
            );
        } catch (RuntimeException exception) {
            fileChooser.setInitialDirectory(
                    new File(System.getProperty("user.home"))
            );
        }
    }
}
