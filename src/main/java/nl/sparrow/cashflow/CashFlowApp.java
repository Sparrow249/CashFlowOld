package nl.sparrow.cashflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.sparrow.cashflow.gui.menu.AccountMenuSelection;
import nl.sparrow.cashflow.logic.services.AccountService;
import nl.sparrow.cashflow.logic.utils.TestScenarioTDateFilter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CashFlowApp extends Application
{
   public static final Logger LOGGER = Logger.getLogger(CashFlowApp.class.getName());

   private static Stage mainStage;
   private static State state;

   public static class State extends Observable
   {
      private final AccountService       accountService;
      private       AccountMenuSelection accountMenuSelection;


      private State(AccountService accountService)
      {
         this.accountService = accountService;
      }


      public AccountService getAccountService()
      {
         return accountService;
      }


      public AccountMenuSelection getAccountMenuSelection()
      {
         return accountMenuSelection;
      }


      public void setAccountMenuSelection(AccountMenuSelection accountMenuSelection)
      {
         this.accountMenuSelection = accountMenuSelection;
         setChanged();
         notifyObservers();
         LOGGER.fine("Changed selecetion to: "+this.accountMenuSelection.toString());
      }
   }


   public static State getAppState()
   {
      return state;
   }


   public static void main(String[] args)
   {
      setupLogging();
      state = new State(new AccountService());
      TestScenarioTDateFilter.setup(state.getAccountService());
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
      try (InputStream configFile = new FileInputStream("src/main/resources/logging.properties"))
      {
         LogManager.getLogManager().readConfiguration(configFile);
         LOGGER.config("Logging setup");
         LOGGER.fine("user.dir = " + System.getProperty("user.dir"));
      }
      catch (FileNotFoundException e)
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
