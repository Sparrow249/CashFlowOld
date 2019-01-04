package nl.sparrow.cashflow.gui;

public enum View {
    TOOL_BAR("toolBar/toolBar.fxml"),
    ACCOUNT_MENU("menu/accountMenu.fxml"),
    ADD_ACCOUNT_PANE("menu/addAccountPane.fxml"),
    ACCOUNT_OVERVIEW("content/accountOverview/accountOverview.fxml"),
    TRANSACTION_OVERVIEW("content/transactionOverview/transactionOverview.fxml"),
    CSV_UPLOAD_VIEW("dialog/csvUploadView/csvUploadView.fxml");

    private String fxmlPath;

    View(String path) {
        this.fxmlPath = path;
    }

    @Override
    public String toString() {
        return this.fxmlPath;
    }
}
