package main.services;

import main.models.Account;

import java.util.List;

public class AccountFilterService {

    public static Account filter(String iban){
        for(Account account: Account.getAccounts()){
            if(account.getIban().equals(iban)){
                return account;
            }
        }
        return null;
    }

}
