package main.gui.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import main.Modification;
import main.gui.Controller;
import main.gui.Overview;
import main.models.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountMenuController extends Controller implements Overview {
    List<Account> accountList = new ArrayList<>();

    @FXML
    private Accordion menu;

    @FXML
    private AddAccountPaneController addAccountPaneController;

    public void initialize() {
        addAccountPaneController.setParent(this);
    }

    private void createFxml() {
        //TODO: Observer?
        System.out.println("Creating Menu");

        switch (getModificationType()) {
            case CREATE:
                for (Account account : getAccountService().getAllAccounts()) {
                    if (!accountList.contains(account)) {
                        TitledPane titledPane = new TitledPane();
                        titledPane.setText(account.getIban());
                        System.out.println(account.getIban());
                        menu.getPanes().add(titledPane);
                        accountList.add(account);
                        break;
                        //TODO track controllers --> Best to upload template and add values there...
                    }
                }
                break;
            case DELETE:
                for (TitledPane pane : menu.getPanes()) {
                    String paneTitle = pane.getText();
                    if (paneTitle.equals("Add Account")) {
                        continue;
                    }

                    Account account = getAccountService().getAccount(pane.getText());
                    if (account == null) {
                        menu.getPanes().removeAll(pane);
                        accountList.remove(account);
                        break;
                    }
                }
                break;
            case UPDATE:
                break;
        }
    }

    private Modification getModificationType() {
        int accountPanesCount = menu.getPanes().size() - 1;
        int accountCount = getAccountService().getAllAccounts().size();

        if (accountPanesCount < accountCount) {
            return Modification.CREATE;
        } else if (accountPanesCount == accountCount) {
            return Modification.UPDATE;
        } else { //accountPanesCount > accountCount
            return Modification.DELETE;
        }
    }

    @Override
    public void refresh() {
        createFxml();
    }
}
