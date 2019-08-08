package nl.sparrow.cashflow.logic.upload.parsers;

import nl.sparrow.cashflow.logic.exceptions.ExceptionMessage;
import nl.sparrow.cashflow.logic.exceptions.TechnicalException;
import nl.sparrow.cashflow.logic.upload.Bank;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class BankFileCsvParser
{
    public List<CSVRecord> parse(File file, Bank bank)
    {
        CSVFormat csvFormat = bank.getCsvFormat()
            .withHeader()
            .withIgnoreHeaderCase();

        try (FileReader reader = new FileReader(file))
        {
            return CSVParser.parse(reader, csvFormat).getRecords();
        }
        catch (IOException e)
        {
            throw new TechnicalException(ExceptionMessage.UNPARSABLE_CSV, e);
        }
    }
}
