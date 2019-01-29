package nl.sparrow.cashflow.gui;

public enum ContentView
{
    ACCOUNT_OVERVIEW("content/accountOverview/accountOverview.fxml"),
    TRANSACTION_OVERVIEW("content/transactionOverview/transactionOverview.fxml"),
    CSV_UPLOAD_VIEW("dialog/csvUploadView/csvUploadView.fxml");

    private String fxmlPath;

    ContentView(String path) {
        this.fxmlPath = path;
    }

    public String getFxmlPath()
    {
        return fxmlPath;
    }
}
