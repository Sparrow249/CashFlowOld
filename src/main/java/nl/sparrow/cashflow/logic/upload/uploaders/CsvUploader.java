package nl.sparrow.cashflow.logic.upload.uploaders;

import nl.sparrow.cashflow.logic.upload.Bank;
import nl.sparrow.cashflow.logic.upload.consumers.CsvDataConsumer;
import nl.sparrow.cashflow.logic.upload.parsers.BankFileCsvParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.List;

public class CsvUploader implements BankFileUploader
{
    private final static BankFileCsvParser bankFileCsvParser = new BankFileCsvParser();

    @Override
    public void upload(File file, Bank bank)
    {
        List<CSVRecord> csvRecordList = bankFileCsvParser.parse(file, bank);

        CsvDataConsumer consumer = bank.getCsvDataConsumer();

        consumer.accept(csvRecordList);
    }
}
