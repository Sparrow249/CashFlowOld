package nl.sparrow.cashflow.gui.content.transactionOverview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.gui.Controller;
import nl.sparrow.cashflow.gui.dataModels.TransactionModel;
import nl.sparrow.cashflow.gui.menu.AccountMenuSelection;
import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.models.Transaction;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TransactionOverviewController extends Controller implements Observer
{
   @FXML
   private TableView<TransactionModel> tbTransactions;

   @FXML
   private TableColumn<TransactionModel, String> tbcDate;
   @FXML
   private TableColumn<TransactionModel, String> tbcDescription;
   @FXML
   private TableColumn<TransactionModel, String> tbcAmount;

   private ObservableList<TransactionModel> transactions;

   private CashFlowApp.State appState;

   public void initialize()
   {
      this.appState = CashFlowApp.getAppState();

      appState.addObserver(this);
      appState.getAccountService().addObserver(this);
      appState.getAccountService().getAllAccounts().stream().forEach(account -> account.addObserver(this));

      tbcDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
      tbcAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
      tbcDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
      update(null, null);
   }


   private List<TransactionModel> getTableData()
   {
      List<Transaction> transactionList;

      AccountMenuSelection selection = appState.getAccountMenuSelection();
      Account account = selection.getAccount();
      Integer year = selection.getYear();
      Month month = selection.getMonth();

      if(account == null)
      {
         transactionList = appState.getAccountService().getAllAccounts().get(0).getAllTransactions();
      }
      else{
         transactionList = account.getTransactions(transaction -> year != null ? transaction.getDate().getYear() == year : true && month != null ? transaction.getDate().getMonth().equals(month) : true);
      }

      List<TransactionModel> tableData = new ArrayList<>();

      for (Transaction transaction : transactionList)
      {
         tableData.add(new TransactionModel(transaction));
      }

      return tableData;
   }


   @Override
   public void update(Observable o, Object arg)
   {
      this.appState = CashFlowApp.getAppState();
      transactions = FXCollections.observableArrayList(getTableData());
      tbTransactions.setItems(transactions);
   }
}
