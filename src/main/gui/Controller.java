package main.gui;

import main.models.Account;
import main.services.AccountService;

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

    public void refresh() {
        parent.refresh();
    }

}
