package nl.sparrow.cashflow.gui.menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.models.Transaction;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.function.Function;

public class YearPaneController implements Initializable, Observer
{
   @FXML
   private Accordion menu;

   private final Account account;

   private final Map<Integer, TitledPane>            yearPaneMap       = new HashMap<>();
   private final Map<TitledPane, YearPaneController> paneControllerMap = new HashMap<>();


   public YearPaneController(Account account)
   {
      this.account = account;
      account.addObserver(this);
   }


   private void track(Integer year, TitledPane pane, YearPaneController controller)
   {
      if (yearPaneMap.get(year) == null)
      {
         yearPaneMap.put(year, pane);
         paneControllerMap.put(pane, controller);
      }
   }


   private void untrack(Integer year)
   {
      TitledPane pane = yearPaneMap.get(year);
      if (pane != null)
      {
         paneControllerMap.remove(pane);
         yearPaneMap.remove(year);
      }
   }


   //TODO Make generic for reuse in YearPaneController
   private void addMenuPane(Integer year)
   {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuPane.fxml"));
      try
      {
         TitledPane pane = fxmlLoader.load();
         pane.setText(year.toString());
         menu.getPanes().add(pane);
         track(year, pane, fxmlLoader.getController());
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }


   @Override
   public void update(Observable o, Object arg)
   {
      List<Transaction> transactions = account.getAllTransactions();
      transactions.stream()
         .map(transaction -> transaction.getDate().getYear())
         .distinct()
         .forEach(year -> {
            if (!yearPaneMap.containsKey(year))
            {
               addMenuPane(year);
            }
         });

      yearPaneMap.values().stream()
         .filter(pane -> !menu.getPanes().contains(pane))
         .forEach(deprecatedPane -> {
            yearPaneMap.forEach((year, titledPane) -> {
               if (titledPane == deprecatedPane)
               {
                  menu.getPanes().remove(titledPane);
                  untrack(year);
               }
            });
         });
   }


   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      update(null, null);
   }
}
