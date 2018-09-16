package main.gui.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import main.gui.CashFlowApp;
import main.gui.Controller;
import main.models.Account;

import java.util.*;

public class AccountMenuController extends Controller implements Observer {
//    List<Account> accountList = new ArrayList<>();
    Map<Account, TitledPane> accountList = new HashMap<>();

    @FXML
    private Accordion menu;

//    @FXML
//    private AddAccountPaneController addAccountPaneController;

    public void initialize() {
        CashFlowApp.getAccountService().addObserver(this);
    }

//    private void refresh() {
//        //TODO: Observer?
//        System.out.println("Creating Menu");
//
//        //for all accounts in accountlist, check if all have a pane
//        for (Account account : accountList) {
//            boolean isNew = menu.getPanes().stream().map(pane -> pane.getText()).anyMatch(text -> text == account.getIban());
//        }
//
//        //for all panes in menu.getPanes, check if an account exists
//        for ()
//    }
//
//        switch (getModificationType()) {
//            case CREATE:
//                for (Account account : getAccountService().getAllAccounts()) {
//                    if (!accountList.contains(account)) {
//                        TitledPane titledPane = new TitledPane();
//                        titledPane.setText(account.getIban());
//                        System.out.println(account.getIban());
//                        menu.getPanes().add(titledPane);
//                        accountList.add(account);
//                        break;
//                        //TODO track controllers --> Best to upload template and add values there...
//                    }
//                }
//                break;
//            case DELETE:
//                for (TitledPane pane : menu.getPanes()) {
//                    String paneTitle = pane.getText();
//                    if (paneTitle.equals("Add Account")) {
//                        continue;
//                    }
//
//                    Account account = getAccountService().getAccount(pane.getText());
//                    if (account == null) {
//                        menu.getPanes().removeAll(pane);
//                        accountList.remove(account);
//                        break;
//                    }
//                }
//                break;
//            case UPDATE:
//                break;
//        }
//    }



    @Override
    public void update(Observable o, Object arg) {
        List<Account> accounts = CashFlowApp.getAccountService().getAllAccounts();
        accounts.stream()
                .forEach(account -> {
                    if(!accountList.containsKey(account)) {
                        TitledPane titledPane = new TitledPane();
                        titledPane.setText(account.getIban());
                        System.out.println("Pane created: "+ account.getIban());
                        menu.getPanes().add(titledPane);
                        accountList.put(account, titledPane);
                    }
                });

        accountList.values().stream()
                .filter(pane -> !menu.getPanes().contains(pane))
                .forEach(deprecatedPane -> {
                    accountList.forEach((account, titledPane) -> {
                        if(titledPane == deprecatedPane) {
                            menu.getPanes().removeAll(titledPane);
                            accountList.remove(account);
                        }
                    });
                });

        System.out.println("accountList updated");
    }
}
