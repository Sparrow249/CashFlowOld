package nl.sparrow.gui.content.transactionOverview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.sparrow.gui.Controller;
import nl.sparrow.gui.Overview;
import nl.sparrow.gui.dataModels.TransactionModel;
import nl.sparrow.logic.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionOverviewController extends Controller implements Overview {

//    @FXML
//    private VBox vBox;

    @FXML
    private TableView<TransactionModel> tbTransactions;

    //    @FXML
//    private TableColumn<Transaction, String> tbcDate;
    @FXML
    private TableColumn<TransactionModel, String> tbcDescription;
    @FXML
    private TableColumn<TransactionModel, String> tbcAmount;

    private ObservableList<TransactionModel> transactions;

    public void initialize() {
//        tbcDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tbcDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tbcAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));


    }

    private List<TransactionModel> getTableData() {
        List<Transaction> transactionList = getAccountService().getAllAccounts().get(0).getAllTransactions();
        List<TransactionModel> tableData = new ArrayList<>();

        for (Transaction transaction : transactionList) {
            tableData.add(new TransactionModel(transaction));
        }

        return tableData;
    }


    private void updateTableData(List<TransactionModel> data) {
        transactions = FXCollections.observableArrayList(data);
    }


    @Override
    public void refresh() {
        updateTableData(getTableData());
        tbTransactions.setItems(transactions);
    }
}
