package main.gui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CashFlowAppController {

    @FXML
    private AnchorPane topPane;

    @FXML
    private AnchorPane leftPane;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private ToolBarController topPaneController;

    public void initialize() {
        topPaneController.setParent(this);
    }

    public void switchScene() throws IOException {
        AnchorPane otherScene = FXMLLoader.load(getClass().getResource("otherScene.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(otherScene);
        //TODO: how to access the controller this way?

    }


    //TODO: switch from one fxml to another within a shown scene --> Controller communication?
}
