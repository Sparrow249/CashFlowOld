package nl.sparrow.cashflow.gui.toolBar;

import javafx.event.ActionEvent;
import nl.sparrow.cashflow.gui.Controller;
import nl.sparrow.cashflow.gui.View;

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


