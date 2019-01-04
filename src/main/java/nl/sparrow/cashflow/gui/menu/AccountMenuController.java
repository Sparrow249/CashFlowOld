package nl.sparrow.cashflow.gui.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.gui.Controller;
import nl.sparrow.cashflow.logic.models.Account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class AccountMenuController extends Controller implements Observer
{
   Map<Account, TitledPane> accountList = new HashMap<>();

   @FXML
   private Accordion menu;

   public void initialize()
   {
      CashFlowApp.getAccountService().addObserver(this);
   }

   @Override
   public void update(Observable o, Object arg)
   {
      List<Account> accounts = CashFlowApp.getAccountService().getAllAccounts();
      accounts.stream()
         .forEach(account -> {
            if (!accountList.containsKey(account))
            {
               //TODO use template and add values there -> track controllers --> template factory?
               TitledPane titledPane = new TitledPane();
               titledPane.setText(account.getIban());
               menu.getPanes().add(titledPane);
               accountList.put(account, titledPane);
            }
         });

      accountList.values().stream()
         .filter(pane -> !menu.getPanes().contains(pane))
         .forEach(deprecatedPane -> {
            accountList.forEach((account, titledPane) -> {
               if (titledPane == deprecatedPane)
               {
                  menu.getPanes().removeAll(titledPane);
                  accountList.remove(account);
               }
            });
         });
   }
}
