package nl.sparrow.cashflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.sparrow.cashflow.gui.ContentView;
import nl.sparrow.cashflow.logic.upload.Bank;
import nl.sparrow.cashflow.logic.upload.CsvUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class CashFlowApp extends Application
{
    public static final Logger logger = LoggerFactory.getLogger(CashFlowApp.class);

    public static void main(String[] args)
    {
//        setupLogging();
        new CsvUploader().upload(new File("src/main/resources/raboTest.csv"), Bank.RABO);
        logger.debug("testData set");
        launch(args);
    }


    @Override
    public void start(Stage primaryStage)
        throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(ContentView.NAVIGATION_VIEW.getFxmlURL());
        Parent root = fxmlLoader.load();

        root.getStylesheets()
            .add(getClass().getResource("gui/style.css")
                     .toExternalForm());
        primaryStage.setTitle("CashFlow");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();

        //TODO: change window titlebar layout?
    }
//
//
//    private static void setupLogging()
//    {
//        try (InputStream configFile = new FileInputStream("src/main/resources/logging.properties"))
//        {
//            LogManager.getLogManager()
//                .readConfiguration(configFile);
//            LOGGER.config("Logging setup");
//            LOGGER.fine("user.dir = " + System.getProperty("user.dir"));
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//            LOGGER.log(Level.SEVERE, e.getMessage(), e);
//        }
//    }

}
