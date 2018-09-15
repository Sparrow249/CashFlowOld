package main.gui.controllers;

import main.gui.controllers.CashFlowAppController;
import main.services.AccountService;

public abstract class Controller {
    protected CashFlowAppController parent;

    public void setParent(CashFlowAppController parent){
        this.parent = parent;
    }
}
