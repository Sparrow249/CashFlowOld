package nl.sparrow.gui;

import nl.sparrow.logic.models.Account;
import nl.sparrow.logic.services.AccountService;

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

    public void switchScene(String fxml) {
        parent.switchScene(fxml);
    }

}
