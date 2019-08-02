package nl.sparrow.cashflow.gui.content.transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.sparrow.cashflow.logic.transaction.Transaction;
import nl.sparrow.cashflow.logic.transaction.TransactionDao;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class TransactionTableViewController
{
    @FXML
    private TableView<Transaction> tbTransactions;

    @FXML
    private TableColumn<Transaction, LocalDate> tbcDate;
    @FXML
    private TableColumn<Transaction, String> tbcDescription;
    @FXML
    private TableColumn<Transaction, Double> tbcAmount;

    public void initialize()
    {
        tbcDescription.setCellValueFactory(new PropertyValueFactory<>(Transaction.InstanceField.description));
        tbcAmount.setCellValueFactory(new PropertyValueFactory<>(Transaction.InstanceField.amount));
        tbcDate.setCellValueFactory(new PropertyValueFactory<>(Transaction.InstanceField.date));
        tbTransactions.getSortOrder().add(tbcDate);
        update();
    }


    private ObservableList<Transaction> getTableData()
    {
        return FXCollections.observableArrayList(TransactionDao.getInstance()
                                                     .fetchAll());
    }

    private SortedList<Transaction> getSortedData(ObservableList<Transaction> dataList, Comparator<Transaction> comparator)
    {
        SortedList<Transaction> sortedList = new SortedList<>(dataList, comparator);
        sortedList.comparatorProperty().bind(tbTransactions.comparatorProperty());
        return sortedList;
    }

    private void update()
    {
        SortedList<Transaction> sortedData = getSortedData(getTableData(), Comparator.comparing(Transaction::getDate));
        tbTransactions.setItems(sortedData);
    }
}
