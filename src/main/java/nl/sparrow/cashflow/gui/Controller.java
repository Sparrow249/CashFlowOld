package nl.sparrow.cashflow.gui;

import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.services.AccountService;

public abstract class Controller {
    protected Controller parent;

    public void setParent(Controller parent) {
        this.parent = parent;
    }

    public AccountService getAccountService() {
        return parent.getAccountService();
    }

    public Account getSelectedAccount() {
        return parent.getSelectedAccount();
    }

    public void switchScene(ContentView contentView) {
        parent.switchScene(contentView);
    }

}
