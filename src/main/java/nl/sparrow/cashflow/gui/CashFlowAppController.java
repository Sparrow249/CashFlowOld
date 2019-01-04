package nl.sparrow.cashflow.gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import nl.sparrow.cashflow.gui.menu.AccountMenuController;
import nl.sparrow.cashflow.gui.toolBar.ToolBarController;

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

    @FXML
    private AccountMenuController accountMenuController;

    private Controller contentController;

    public void initialize() {

        toolBarController.setParent(this);
//        accountMenuController.setParent(this);
        switchScene(View.CSV_UPLOAD_VIEW.toString());
    }

    @Override
    public void switchScene(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
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
