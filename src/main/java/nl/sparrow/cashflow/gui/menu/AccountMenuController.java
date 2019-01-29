package nl.sparrow.cashflow.gui.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.gui.Controller;
import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.models.Transaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class AccountMenuController extends Controller implements Observer
{
   @FXML
   private Accordion menu;

   //TODO make more linked
   private final Map<Account, TitledPane>            accountPaneMap    = new HashMap<>();
   private final Map<TitledPane, YearPaneController> paneControllerMap = new HashMap<>();



   public void initialize()
   {
      CashFlowApp.getAccountService().addObserver(this);
      update(null, null);
   }


   private void track(Account account, TitledPane pane, YearPaneController controller)
   {
      if (accountPaneMap.get(account) == null)
      {
         accountPaneMap.put(account, pane);
         paneControllerMap.put(pane, controller);
      }
   }

   private void untrack(Account account){
      TitledPane pane = accountPaneMap.get(account);
      if(pane != null){
         paneControllerMap.remove(pane);
         accountPaneMap.remove(account);
      }
   }


   //TODO Make generic for reuse in YearPaneController
   private void addMenuPane(Account account)
   {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuPane.fxml"));
      try
      {
         YearPaneController yearMenuController = new YearPaneController(account);
         fxmlLoader.setController(yearMenuController);
         TitledPane pane = fxmlLoader.load();
         pane.setText(account.getIban());
         menu.getPanes().add(pane);
         track(account, pane, fxmlLoader.getController());
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }


   @Override
   public void update(Observable o, Object arg)
   {
      List<Account> accounts = CashFlowApp.getAccountService().getAllAccounts();
      accounts.stream()
         .forEach(account -> {
            if (!accountPaneMap.containsKey(account))
            {
               addMenuPane(account);
            }
         });

      accountPaneMap.values().stream()
         .filter(pane -> !menu.getPanes().contains(pane))
         .forEach(deprecatedPane -> {
            accountPaneMap.forEach((account, titledPane) -> {
               if (titledPane == deprecatedPane)
               {
                  menu.getPanes().remove(titledPane);
                  untrack(account);
               }
            });
         });
   }
}
