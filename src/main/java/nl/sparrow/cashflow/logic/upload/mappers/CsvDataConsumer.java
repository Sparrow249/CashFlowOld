package nl.sparrow.cashflow.logic.upload.mappers;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.function.Consumer;

public interface CsvDataConsumer extends Consumer<List<CSVRecord>>
{
    boolean hasHeader(List<String> header);
}
