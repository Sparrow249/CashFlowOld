package nl.sparrow.cashflow.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.logic.exceptions.ExceptionMessage;
import nl.sparrow.cashflow.logic.exceptions.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NavigationViewController implements NavigationComponent
{
    @FXML
    private BorderPane base;

    public void initialize()
    {
        switchScene(ContentView.TRANSACTION_TABLE_VIEW);
    }

    @Override
    public void switchScene(ContentView contentView)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(contentView.getFxmlURL());
        try
        {
            AnchorPane view = fxmlLoader.load();
            base.setCenter(view);
        }
        catch (IOException e)
        {
            throw new TechnicalException(ExceptionMessage.SWITCH_SCENE_ERROR, e);
        }
    }

    public void openTransactionOverview(ActionEvent actionEvent)
    {
        switchScene(ContentView.TRANSACTION_TABLE_VIEW);
    }

    public void openCsvUploadView(ActionEvent actionEvent)
    {
        switchScene(ContentView.CSV_UPLOAD_VIEW);
    }
}
