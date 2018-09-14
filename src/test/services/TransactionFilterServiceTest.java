package services;

import main.models.Account;
import main.models.Category;
import main.models.Transaction;
import main.services.TransactionFilterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionFilterServiceTest {

    private static Category testCategory1;
    private static Category testCategory2;
    private static Account testAccount;
    private static Transaction testTransaction1;
    private static Transaction testTransaction2;
    private static Transaction testTransaction3;
    private static List<Transaction> transactionList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        testCategory1 = new Category("one");
        testCategory2 = new Category("two");

        testAccount = new Account("test");
        testAccount.addTransaction(1.00, null);
        testAccount.addTransaction(2.00, testCategory1);
        testAccount.addTransaction(-3.00, testCategory2);
        testTransaction1 = testAccount.getTransactions().get(0);
        testTransaction2 = testAccount.getTransactions().get(1);
        testTransaction3 = testAccount.getTransactions().get(2);

        transactionList = testAccount.getTransactions();
    }

    @Test
    public void testFilterCategory_filterNull() {
        List<Transaction> result = TransactionFilterService.filter(transactionList, null);

        assertEquals(1, result.size());
        assertTrue(result.contains(testTransaction1));
    }

    @Test
    public void testFilterCategory_filterExistingCategory() {
        List<Transaction> result = TransactionFilterService.filter(transactionList, testCategory1);
        assertEquals(1, result.size());
        assertTrue(result.contains(testTransaction2));
    }


    @AfterEach
    public void done() {
        Account.getAccounts().removeAll(Account.getAccounts());
        Category.getCategories().removeAll(Category.getCategories());
        assertTrue(Category.getCategories().isEmpty());
        assertTrue(Account.getAccounts().isEmpty());
    }
}
