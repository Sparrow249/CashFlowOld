package main.gui.content.transactionOverview;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import main.gui.Controller;
import main.gui.Overview;
import main.models.Account;
import main.models.Transaction;

public class TransactionOverviewController extends Controller implements Overview {

    @FXML
    private VBox vBox;

    private void createFxml() {
        vBox.getChildren().clear();
        Account selectedAccount = getSelectedAccount();
        if (selectedAccount == null) {
            for (Account account : getAccountService().getAllAccounts()) {
                updateTransactionCards(account);
            }
        } else {
            updateTransactionCards(selectedAccount);
        }
    }

    private void updateTransactionCards(Account account) {
        for (Transaction transaction : account.getAllTransactions()) {
            createTransactionCard(transaction);
        }
    }

    private void createTransactionCard(Transaction transaction) {
        VBox transactionCard = new VBox();
        transactionCard.getStyleClass().add("transaction-card");

        //Create title bar
        HBox titleBar = new HBox();
        titleBar.getStyleClass().add("title-bar");
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        Label date = new Label("Date");
        Label amount = new Label("€" + transaction.getAmount());

        titleBar.getChildren().addAll(date, region, amount);

        //Create description box
        StackPane descriptionBox = new StackPane();
        descriptionBox.getStyleClass().add("description-box");
        descriptionBox.setAlignment(Pos.TOP_LEFT);


        Label description = new Label(transaction.getDescription());
        description.setWrapText(true);

        descriptionBox.getChildren().addAll(description);


        transactionCard.getChildren().addAll(titleBar, descriptionBox);
        vBox.getChildren().add(transactionCard);
        //TODO: track controllers
    }


    @Override
    public void refresh() {
        createFxml();
    }
}
