package main.services;

import main.models.Category;
import main.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionFilterService {
    public static List<Transaction> filter(List<Transaction> transactionList , Category category){
        List<Transaction> result = new ArrayList<>();

        for(Transaction transaction: transactionList){
            if(transaction.getCategory() == category){
                result.add(transaction);
            }
        }

        return result;
    }
}
