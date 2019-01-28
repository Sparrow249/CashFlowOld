package nl.sparrow.cashflow.gui.menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nl.sparrow.cashflow.gui.CashFlowApp;
import nl.sparrow.cashflow.gui.Controller;
import nl.sparrow.cashflow.logic.exceptions.InvalidIbanException;

public class AddAccountPaneController extends Controller
{

   @FXML
   private TextField inputAccountIban;


   public void addAccount(ActionEvent actionEvent)
   {
      String iban = inputAccountIban.getText().toUpperCase();

      try
      {
         CashFlowApp.getAccountService().addAccount(iban);
      }
      catch (InvalidIbanException ex)
      {
         //TODO show message invalid iban to user
      }
      finally
      {
         inputAccountIban.setText("");
      }
   }


}
