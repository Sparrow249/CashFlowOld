package nl.sparrow.cashflow.gui.menu;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.gui.Controller;
import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.models.Transaction;
import nl.sparrow.cashflow.logic.services.AccountService;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class AccountTreeMenuController extends Controller implements Observer
{
   @FXML
   private TreeView menu;

   private final TreeItem<String> accountsTree = new TreeItem<>();

   private TreeItem<String> selectedItem = null;

   public void initialize()
   {
      CashFlowApp.getAccountService().addObserver(this);
      menu.setRoot(accountsTree);
      update(null, null);
   }

   public void onMouseClicked(MouseEvent mouseEvent){
      TreeItem<String> newSelectedItem = (TreeItem) menu.getSelectionModel().getSelectedItem();

      if (newSelectedItem != null && !newSelectedItem.equals(selectedItem))
      {
         this.selectedItem = newSelectedItem;

         String sAccount = null;
         String sYear = null;
         String sMonth = null;

         switch (menu.getTreeItemLevel(selectedItem))
         {
            case 1:
               sAccount = selectedItem.getValue();
               break;
            case 2:
               sAccount = selectedItem.getParent().getValue();
               sYear = selectedItem.getValue();
               break;
            case 3:
               sAccount = selectedItem.getParent().getParent().getValue();
               sYear = selectedItem.getParent().getValue();
               sMonth = selectedItem.getValue();
               break;
         }

         List<Transaction> transactions = getTransactions(sAccount, sYear, sMonth);

         System.out.println("-------");
         transactions.stream().forEach(transaction -> System.out.println(transaction));
      }
   }

   private List<Transaction> getTransactions(String sAccount, String sYear, String sMonth){
      Account account = CashFlowApp.getAccountService().getAccount(sAccount);
      List<Transaction> transactions = account.getTransactions(transaction -> sYear != null ? transaction.getDate().getYear() == Integer.parseInt(sYear) : true
                                                                              && sMonth != null ? transaction.getDate().getMonth().name().equals(sMonth) : true
      );

      return transactions;
   }

   @Override
   public void update(Observable o, Object arg)
   {
      AccountService accountService = CashFlowApp.getAccountService();

      //update account tabs
      List<Account> accounts = CashFlowApp.getAccountService().getAllAccounts();
      TreeViewHelper.syncTreeItem(accountsTree, accounts, account -> account.getIban());

      accountsTree.getChildren().stream()
         .forEach(tiAccount -> {
            Account account = accountService.getAccount(tiAccount.getValue());

            //update year tabs
            List<Integer> years = account.getAllTransactions().stream()
               .map(transaction -> transaction.getDate().getYear())
               .distinct()
               .sorted((y1, y2) -> y2-y1)
               .collect(Collectors.toList());

            TreeViewHelper.syncTreeItem(tiAccount, years, year -> year.toString());

            //update month tabs
            tiAccount.getChildren().stream()
               .forEach(tiYear -> {
                  List<Month> months = account.getTransactions(transaction -> transaction.getDate().getYear() == Integer.parseInt(tiYear.getValue())).stream()
                     .map(transaction -> transaction.getDate().getMonth())
                     .distinct()
                     .sorted(Comparator.comparing(Month::getValue))
                     .collect(Collectors.toList());

                  TreeViewHelper.syncTreeItem(tiYear, months, month -> month.getDisplayName(TextStyle.FULL, Locale.getDefault()));
               });
         });

      CashFlowApp.LOGGER.fine("Updated menu treeview");
   }
}
