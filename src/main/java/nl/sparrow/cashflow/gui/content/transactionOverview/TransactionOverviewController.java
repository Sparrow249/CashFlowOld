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
import nl.sparrow.cashflow.logic.models.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TransactionOverviewController extends Controller implements Observer
{

   //    @FXML
   //    private VBox vBox;

   @FXML
   private TableView<TransactionModel> tbTransactions;

   //    @FXML
   //    private TableColumn<Transaction, String> tbcDate;
   @FXML
   private TableColumn<TransactionModel, String> tbcDate;
   @FXML
   private TableColumn<TransactionModel, String> tbcDescription;
   @FXML
   private TableColumn<TransactionModel, String> tbcAmount;

   private ObservableList<TransactionModel> transactions;


   public void initialize()
   {
      CashFlowApp.getAccountService().getAllAccounts().stream().forEach(account -> account.addObserver(this));
      //        tbcDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
      tbcDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
      tbcAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
      tbcDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
      update(null, null);
   }


   private List<TransactionModel> getTableData()
   {
      List<Transaction> transactionList = CashFlowApp.getAccountService().getAllAccounts().get(0).getAllTransactions();
      List<TransactionModel> tableData = new ArrayList<>();

      for (Transaction transaction : transactionList)
      {
         tableData.add(new TransactionModel(transaction));
      }

      return tableData;
   }


   private void updateTableData(List<TransactionModel> data)
   {
      transactions = FXCollections.observableArrayList(data);
   }


   @Override
   public void update(Observable o, Object arg)
   {
      transactions = FXCollections.observableArrayList(getTableData());
      tbTransactions.setItems(transactions);
   }
}
