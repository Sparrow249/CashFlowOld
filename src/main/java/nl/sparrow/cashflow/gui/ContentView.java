package nl.sparrow.cashflow.gui;

import java.net.URL;

public enum ContentView
{
    NAVIGATION_VIEW("navigationView.fxml"),
    ACCOUNT_OVERVIEW("content/account/accountOverview.fxml"),
    TRANSACTION_TABLE_VIEW("content/transaction/transactionTableView.fxml"),
    CSV_UPLOAD_VIEW("dialog/csvUploadView/csvUploadView.fxml");

    private String fxmlPath;

    ContentView(String path)
    {
        this.fxmlPath = path;
    }

    public URL getFxmlURL()
    {
        return getClass().getResource(fxmlPath);
    }
}
