package nl.sparrow.cashflow.logic.services;

import nl.sparrow.cashflow.logic.exceptions.InvalidIbanException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Observer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest
{
//   @Mock
   private AccountService accountService;

   private static final String IBAN = "NL00TEST0123456789";

   //TODO test if observers are notified -> aka their update method is called

   @Before
   public void setup()
   {
      accountService = new AccountService();
   }

   @Test
   public void testAddAccount_validIban()
   {
      accountService.addAccount(IBAN);

      assertNotNull("expected account to be created", accountService.getAccount(IBAN));
   }


   @Test
   public void testAddAccount_validIbanLowerCase()
   {
      final String IBAN_LOWER = IBAN.toLowerCase();

      accountService.addAccount(IBAN_LOWER);

      assertNotNull("expected account to be created", accountService.getAccount(IBAN));
   }


   @Test
   public void testAddAccount_duplicateIban()
   {
      accountService.addAccount(IBAN);
      accountService.addAccount(IBAN);

      assertEquals("expected only one account to exist", 1, accountService.getAllAccounts().size());
   }


   @Test(expected = InvalidIbanException.class)
   public void testAddAccount_invalidIban()
   {
      final String IBAN_INVALID = "INVALID_TEST_IBAN";

      accountService.addAccount(IBAN_INVALID);
   }
}
