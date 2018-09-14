package main.gui;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class ToolBarController {
    private Object parent;

    @FXML
    public StackPane ToolBarPane;

    public void test(){
        System.out.println(" It worked!! ");
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }
}


