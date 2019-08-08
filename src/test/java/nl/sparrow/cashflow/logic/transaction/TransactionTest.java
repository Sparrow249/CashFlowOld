package nl.sparrow.cashflow.logic.transaction;

import nl.sparrow.cashflow.logic.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TransactionTest
{
    private static final String IBAN = "IBAN";
    private static final String IBAN_OTHER = "IBAN_OTHER";
    private static final String NAME_OTHER = "NAME_OTHER";
    private static final Double AMOUNT = 12.34;
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final LocalDate DATE = LocalDate.now();
    private static final Category CATEGORY = new Category("CATEGORY");

    @Test
    void testBuilder(){
        Transaction transaction = new Transaction.Builder()
            .setIban(IBAN)
            .setIbanOther(IBAN_OTHER)
            .setNameOther(NAME_OTHER)
            .setAmount(AMOUNT)
            .setDescription(DESCRIPTION)
            .setDate(DATE)
            .setCategory(CATEGORY)
            .build();

        assertThat(transaction.getIban(), is(IBAN));
        assertThat(transaction.getIbanOther(), is(IBAN_OTHER));
        assertThat(transaction.getNameOther(), is(NAME_OTHER));
        assertThat(transaction.getAmount(), is(AMOUNT));
        assertThat(transaction.getDescription(), is(DESCRIPTION));
        assertThat(transaction.getDate(), is(DATE));
        assertThat(transaction.getCategory(), is(CATEGORY));
    }

   @Test
    void testBuilder_noRequiredField(){
        Set<Transaction.Builder> testSet = new HashSet<>();

        testSet.add(new Transaction.Builder()
            .setIbanOther(IBAN_OTHER)
            .setAmount(AMOUNT)
            .setDate(DATE)
        );
        testSet.add(new Transaction.Builder()
                        .setIban(IBAN)
                        .setAmount(AMOUNT)
                        .setDate(DATE)
        );
        testSet.add(new Transaction.Builder()
                        .setIbanOther(IBAN)
                        .setIbanOther(IBAN_OTHER)
                        .setDate(DATE)
        );
        testSet.add(new Transaction.Builder()
                        .setIbanOther(IBAN)
                        .setIbanOther(IBAN_OTHER)
                        .setAmount(AMOUNT)
        );

        testSet.forEach(builder ->
                            assertThrows( NullPointerException.class, builder::build));
    }


    @Test
    void testBuilder_noUnrequiredField(){
        Transaction transaction = new Transaction.Builder()
            .setIban(IBAN)
            .setIbanOther(IBAN_OTHER)
            .setAmount(AMOUNT)
            .setDate(DATE)
            .build();

        assertThat("nameOther should not be required", transaction.getNameOther(), is(nullValue()));
        assertThat("description should not be required", transaction.getDescription(), is(nullValue()));
        assertThat("null Category should be replaced with Category.other", transaction.getCategory(), is(Category.OTHER));
    }


    @Test
    void testChangeCategory()
    {
        Transaction transactionOther = new Transaction.Builder()
            .setIban(IBAN)
            .setIbanOther(IBAN_OTHER)
            .setNameOther(NAME_OTHER)
            .setAmount(AMOUNT)
            .setDescription(DESCRIPTION)
            .setDate(DATE)
            .setCategory(Category.OTHER)
            .build();

        Transaction transactionCategory = transactionOther.changeCategory(CATEGORY);

        assertThat(transactionCategory.getIban(), is(IBAN));
        assertThat(transactionCategory.getIbanOther(), is(IBAN_OTHER));
        assertThat(transactionCategory.getNameOther(), is(NAME_OTHER));
        assertThat(transactionCategory.getAmount(), is(AMOUNT));
        assertThat(transactionCategory.getDescription(), is(DESCRIPTION));
        assertThat(transactionCategory.getDate(), is(DATE));
        assertThat(transactionCategory.getCategory(), is(CATEGORY));
    }
}