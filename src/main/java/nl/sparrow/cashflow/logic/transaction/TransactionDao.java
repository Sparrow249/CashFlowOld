package nl.sparrow.cashflow.logic.transaction;

import nl.sparrow.cashflow.logic.models.Dao;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionDao implements Dao<Transaction>
{
    private static List<Transaction> transactionList = new ArrayList<>();

    @Override
    public List<Transaction> fetchAll(Predicate<Transaction> filter)
    {
        return transactionList.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> fetchAll()
    {
        return  transactionList;
    }

    @Override
    public void insert(Transaction transaction)
    {
        transactionList.add(transaction);
    }

    @Override
    public void delete(Transaction transaction)
    {
        transactionList.remove(transaction);
    }
}
