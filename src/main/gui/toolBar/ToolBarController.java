package main.gui.toolBar;

import javafx.event.ActionEvent;
import main.gui.Controller;
import main.gui.View;

public class ToolBarController extends Controller {
    public void openAccountOverview(ActionEvent actionEvent) {
        parent.switchScene(View.ACCOUNT_OVERVIEW.toString());
    }

    public void openTransactionOverview(ActionEvent actionEvent) {
        parent.switchScene(View.TRANSACTION_OVERVIEW.toString());
    }

    public void openCsvUploadView(ActionEvent actionEvent) {
        parent.switchScene(View.CSV_UPLOAD_VIEW.toString());
    }
}


