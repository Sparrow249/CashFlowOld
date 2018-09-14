package models;

import main.models.Account;
import main.models.Category;
import main.models.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private static final String ACCOUNT_IBAN = "NL12RABO0234567890";
    private static final String ACCOUNT_NAME = "Betaalrekening";
    private static final double TRANSACTION_AMOUNT = 0.00;

    private Account testAccount;

    @BeforeEach
    public void setup(){
        testAccount = new Account(ACCOUNT_IBAN, ACCOUNT_NAME);
    }

    @Test
    public void testConstructors() {
        assertEquals(1, Account.getAccounts().size());
        assertEquals(ACCOUNT_IBAN, testAccount.getIban());
        assertEquals(ACCOUNT_NAME, testAccount.getName());

        Account testAccount2 = new Account(ACCOUNT_IBAN);
        assertEquals(1, Account.getAccounts().size());
        assertEquals(ACCOUNT_IBAN, testAccount2.getName());

        testAccount2 = new Account("test");
        assertEquals(2, Account.getAccounts().size());
    }

    @Test
    public void testEquals() {
        Account testAccount2 = new Account("test", ACCOUNT_NAME);
        assertFalse(testAccount.equals(testAccount2));

        testAccount2 = new Account(ACCOUNT_IBAN, "test");
        assertTrue(testAccount.equals(testAccount2));

        testAccount2 = testAccount;
        assertTrue(testAccount.equals(testAccount2));
    }

    @Test
    public void testRemoveAccount() {
        assertEquals(1, Account.getAccounts().size());

        Account.removeAccount(testAccount);
        assertTrue(Category.getCategories().isEmpty());
    }

    @Test
    public void testAddTransaction_amountOnly() {
        assertTrue(testAccount.getTransactions().isEmpty());

        testAccount.addTransaction(TRANSACTION_AMOUNT);
        assertEquals(1, testAccount.getTransactions().size());

        Transaction testTransaction = testAccount.getTransactions().get(0);
        assertEquals(TRANSACTION_AMOUNT, testTransaction.getAmount());
        assertNull(testTransaction.getCategory());
    }

    @Test
    public void testAddTransaction_allParameters() {
        assertTrue(testAccount.getTransactions().isEmpty());

        Category testCategory = new Category();
        testAccount.addTransaction(TRANSACTION_AMOUNT, testCategory);
        assertEquals(1, testAccount.getTransactions().size());

        Transaction testTransaction = testAccount.getTransactions().get(0);
        assertEquals(TRANSACTION_AMOUNT, testTransaction.getAmount());
        assertEquals(testCategory, testTransaction.getCategory());
    }

    @Test
    public void testAddTransaction_twice() {
        assertTrue(testAccount.getTransactions().isEmpty());

        testAccount.addTransaction(TRANSACTION_AMOUNT);
        assertEquals(1, testAccount.getTransactions().size());

        Transaction testTransaction = testAccount.getTransactions().get(0);
        assertEquals(TRANSACTION_AMOUNT, testTransaction.getAmount());
        assertNull(testTransaction.getCategory());

        testAccount.addTransaction(TRANSACTION_AMOUNT);
        assertEquals(2, testAccount.getTransactions().size());
    }

    @AfterEach
    public void done() {
        Account.getAccounts().removeAll(Account.getAccounts());
        Category.getCategories().removeAll(Category.getCategories());
        assertTrue(Category.getCategories().isEmpty());
        assertTrue(Account.getAccounts().isEmpty());
    }
}
