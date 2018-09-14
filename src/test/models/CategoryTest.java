package models;

import main.models.Account;
import main.models.Category;
import main.models.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    private static final String CATEGORY_NAME = "CategoryName";


    @Test
    public void testConstructors() {
        Category testCategory1 = new Category(CATEGORY_NAME);

        assertEquals(1, Category.getCategories().size());
        assertEquals(CATEGORY_NAME, testCategory1.getName());

        Category testCategory2 = new Category(CATEGORY_NAME);

        assertEquals(1, Category.getCategories().size());

        testCategory2 = new Category();

        assertEquals(2, Category.getCategories().size());
        assertEquals("", testCategory2.getName());
    }

    @Test
    public void testEquals() {
        Category testCategory1 = new Category(CATEGORY_NAME);
        Category testCategory2 = new Category("test");

        assertFalse(testCategory1.equals(testCategory2));

        testCategory2 = new Category(CATEGORY_NAME);

        assertTrue(testCategory1.equals(testCategory2));

        testCategory2 = testCategory1;

        assertTrue(testCategory1.equals(testCategory2));
    }

    @Test
    public void testRemoveCategory() {
        Category testCategory = new Category();
        Account testAccount = new Account("");
        testAccount.addTransaction(0.00, testCategory);
        Transaction testTransaction = testAccount.getTransactions().get(0);

        assertEquals(1, Category.getCategories().size());

        Category.removeCategory(testCategory);

        assertTrue(Category.getCategories().isEmpty());
        assertNull(testTransaction.getCategory());
    }

    @AfterEach
    public void done() {
        Account.getAccounts().removeAll(Account.getAccounts());
        Category.getCategories().removeAll(Category.getCategories());
        assertTrue(Category.getCategories().isEmpty());
        assertTrue(Account.getAccounts().isEmpty());
    }
}
