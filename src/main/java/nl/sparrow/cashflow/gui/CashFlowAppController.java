package nl.sparrow.cashflow.gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import nl.sparrow.cashflow.gui.toolBar.ToolBarController;

import javax.tools.Tool;
import java.io.IOException;

public class CashFlowAppController extends Controller {

    @FXML
    private BorderPane base;

    @FXML
    private AnchorPane topPane;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private ToolBarController toolBarController;

    private Controller contentController;

    public void initialize() {
        toolBarController.setParent(this);
        switchScene(ContentView.CSV_UPLOAD_VIEW);
    }

    @Override
    public void switchScene(ContentView contentView) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(contentView.getFxmlPath()));
        try {
            AnchorPane view = fxmlLoader.load();
            base.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
        contentController = fxmlLoader.getController();
        contentController.setParent(this);
    }

}
