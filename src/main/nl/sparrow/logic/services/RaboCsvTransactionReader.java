package nl.sparrow.logic.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RaboCsvTransactionReader {
    private static final String LINE_SEPERATOR = ",\"";

    public List<String[]> readFile(File csvFile) {
        System.out.println("start reading csv file");
        String line;
        String[] csvTransaction;
        List<String[]> csvTransactions = new ArrayList<>();

        //TODO try with resources on BufferedReader

        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            while ((line = reader.readLine()) != null) {
                csvTransaction = line.split(LINE_SEPERATOR);
                for (int i = 0; i < csvTransaction.length; i++) {
                    csvTransaction[i] = csvTransaction[i]
                            .replace("\"", "")
                            .replace(",", ".");
                }
                csvTransactions.add(csvTransaction);
            }
        } catch (IOException exception) {
            exception.getStackTrace();
        }

        System.out.println("finish reading csv file");

        return csvTransactions;
    }

}
