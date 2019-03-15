package nl.sparrow.cashflow.gui.dialog.csvUploadView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.gui.Controller;
import nl.sparrow.cashflow.gui.ContentView;
import nl.sparrow.cashflow.logic.models.Bank;
import nl.sparrow.cashflow.logic.services.CsvUploadService;

import java.io.File;

public class CsvUploadViewController extends Controller
{

   @FXML
   private Button selectCsvFile;


   public void selectCsvFile(ActionEvent actionEvent)
   {
      final FileChooser fileChooser = new FileChooser();
      configureFileChooser(fileChooser);

      //        Stage fileChooserWindow = new Stage();
      File file = fileChooser.showOpenDialog(selectCsvFile.getScene().getWindow());
      if (file != null)
      {
         CsvUploadService csvUploadService = new CsvUploadService(Bank.RABO);
         csvUploadService.upload(file, CashFlowApp.getAppState().getAccountService());
      }

      parent.switchScene(ContentView.TRANSACTION_OVERVIEW);
   }


   private static void configureFileChooser(final FileChooser fileChooser)
   {
      fileChooser.setTitle("Open Resource File");
      fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));

      try
      {
         fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home") + "\\documents")
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
