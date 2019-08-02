package nl.sparrow.cashflow.gui.dialog.csvUploadView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import nl.sparrow.cashflow.logic.upload.Bank;
import nl.sparrow.cashflow.logic.upload.CsvUploader;

import java.io.File;

public class CsvUploadViewController
{

    @FXML
    private Button selectCsvFile;


    public void selectCsvFile(ActionEvent actionEvent)
    {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        //        Stage fileChooserWindow = new Stage();
        File file = fileChooser.showOpenDialog(selectCsvFile.getScene()
                                                   .getWindow());
        if (file != null)
        {
            new CsvUploader().upload(file, Bank.RABO);
        }
    }


    private static void configureFileChooser(final FileChooser fileChooser)
    {
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters()
            .add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

        try
        {
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );
        }
        catch (RuntimeException exception)
        {
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );
        }
    }
}
