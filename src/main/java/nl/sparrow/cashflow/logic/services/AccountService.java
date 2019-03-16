package nl.sparrow.cashflow.logic.services;

import nl.sparrow.cashflow.CashFlowApp;
import nl.sparrow.cashflow.logic.exceptions.InvalidIbanException;
import nl.sparrow.cashflow.logic.models.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

public class AccountService extends Observable {
    private          List<Account> accounts = new ArrayList<>();

    public void addAccount(String iban) {
        iban = iban.toUpperCase();

        if(isValidIban(iban)) {
            Account account = new Account(iban);

            if (!accounts.contains(account)) {
                accounts.add(account);

                setChanged();
                notifyObservers();
                CashFlowApp.LOGGER.fine("Account added: "+ account);
            }
        }
        else{
            CashFlowApp.LOGGER.warning(InvalidIbanException.MESSAGE);
            throw new InvalidIbanException();
        }
    }

    public void removeAccount(Account account) {
        accounts.remove(account);

        setChanged();
        notifyObservers();
        CashFlowApp.LOGGER.fine("Account removed: "+ account);
    }

    public Account getAccount(String iban) {
        for (Account account : accounts) {
            if (account.getIban().equals(iban)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    private boolean isValidIban(String iban){
        String regexIban = "^[a-zA-Z]{2}[0-9]{2}[a-zA-Z]{4}[0-9]{10}$";
        return iban.matches(regexIban);
    }
}
