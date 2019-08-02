package nl.sparrow.cashflow.logic.transaction;

import nl.sparrow.cashflow.logic.models.Dao;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransactionDao implements Dao<Transaction>
{
    private static TransactionDao instance;

    private List<Transaction> transactionList = new ArrayList<>();

    private TransactionDao(){}

    public static TransactionDao getInstance()
    {
        if(instance == null){
            synchronized (TransactionDao.class)
            {
                if (instance == null)
                {
                    instance = new TransactionDao();
                }
            }
        }
        return instance;
    }

    @Override
    public List<Transaction> fetch(Predicate<Transaction> filter)
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
