package nl.sparrow.cashflow.logic.upload;

import nl.sparrow.cashflow.logic.exceptions.ExceptionMessage;
import nl.sparrow.cashflow.logic.exceptions.TechnicalException;
import nl.sparrow.cashflow.logic.upload.mappers.CsvDataConsumer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CsvUploader
{
    public boolean upload(File file, Bank bank)
    {
        CSVFormat csvFormat = bank.getCsvFormat()
            .withHeader()
            .withIgnoreHeaderCase();

        try (FileReader reader = new FileReader(file))
        {
            CSVParser parser = CSVParser.parse(reader, csvFormat);

            CsvDataConsumer consumer = bank.getCsvDataConsumer();

            if (consumer.hasHeader(parser.getHeaderNames()))
            {
                consumer.accept(parser.getRecords());
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (IOException e)
        {
            throw new TechnicalException(ExceptionMessage.UNPARSABLE_CSV, e);
        }
    }
}
