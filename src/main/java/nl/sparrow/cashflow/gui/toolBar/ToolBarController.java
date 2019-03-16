package nl.sparrow.cashflow.gui.toolBar;

import javafx.event.ActionEvent;
import nl.sparrow.cashflow.gui.Controller;
import nl.sparrow.cashflow.gui.ContentView;

public class ToolBarController extends Controller {
    public void openAccountOverview(ActionEvent actionEvent) {
        parent.switchScene(ContentView.ACCOUNT_OVERVIEW);
    }

    public void openTransactionOverview(ActionEvent actionEvent) {
        parent.switchScene(ContentView.TRANSACTION_OVERVIEW);
    }

    public void openCsvUploadView(ActionEvent actionEvent) {
        parent.switchScene(ContentView.CSV_UPLOAD_VIEW);
    }
}


