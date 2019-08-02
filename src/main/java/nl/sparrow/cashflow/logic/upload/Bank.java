package nl.sparrow.cashflow.logic.upload;

import nl.sparrow.cashflow.logic.upload.mappers.CsvDataConsumer;
import nl.sparrow.cashflow.logic.upload.mappers.RaboCsvDataConsumer;
import org.apache.commons.csv.CSVFormat;

public enum Bank
{
    RABO(CSVFormat.EXCEL, new RaboCsvDataConsumer());

    private CSVFormat csvFormat;
    private CsvDataConsumer csvDataConsumer;

    Bank(CSVFormat csvFormat, CsvDataConsumer csvDataConsumer)
    {
        this.csvFormat = csvFormat;
        this.csvDataConsumer = csvDataConsumer;
    }

    public CSVFormat getCsvFormat()
    {
        return csvFormat;
    }

    public CsvDataConsumer getCsvDataConsumer()
    {
        return csvDataConsumer;
    }
}
