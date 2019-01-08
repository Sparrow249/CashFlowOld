package nl.sparrow.cashflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.sparrow.cashflow.logic.models.Account;
import nl.sparrow.cashflow.logic.services.AccountService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CashFlowApp extends Application
{
   private static final Logger LOGGER = Logger.getLogger(CashFlowApp.class.getName());

   private static Stage          mainStage;
   private static AccountService accountService;
   private static Account        selectedAccount;


   public static AccountService getAccountService()
   {
      return accountService;
   }


   public static Account getSelectedAccount()
   {
      return selectedAccount;
   }


   public static void setSelectedAccount(Account selectedAccount)
   {
      CashFlowApp.selectedAccount = selectedAccount;
      LOGGER.info("App account set to: " + selectedAccount.getIban());
   }


   public static void main(String[] args)
   {
      setupLogging();
      accountService = new AccountService();
      launch(args);
   }


   @Override
   public void start(Stage primaryStage)
      throws IOException
   {
      mainStage = primaryStage;
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/cashFlowApp.fxml"));
      Parent root = fxmlLoader.load();

      root.getStylesheets().add(getClass().getResource("gui/style.css").toExternalForm());
      primaryStage.setTitle("CashFlow");
      primaryStage.setScene(new Scene(root, 800, 500));
      primaryStage.show();

      //TODO: change window titlebar layout?
   }


   private static void setupLogging()
   {
      System.out.println(System.getProperty("user.dir"));
      try (InputStream configFile = new FileInputStream("src/main/resources/logging.properties"))
      {
         LOGGER.config("Logging setup");
         LogManager.getLogManager().readConfiguration(configFile);
      }
        catch(FileNotFoundException e)
      {
         e.printStackTrace();
         LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
      catch (IOException e)
      {
         e.printStackTrace();
         LOGGER.log(Level.SEVERE, e.getMessage(), e);
      }
   }

}
