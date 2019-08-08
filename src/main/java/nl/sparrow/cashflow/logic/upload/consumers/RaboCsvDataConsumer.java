package nl.sparrow.cashflow.logic.upload.consumers;

import nl.sparrow.cashflow.logic.transaction.Transaction;
import nl.sparrow.cashflow.logic.transaction.TransactionDao;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RaboCsvDataConsumer implements CsvDataConsumer
{
    private static final Logger logger = LoggerFactory.getLogger(RaboCsvDataConsumer.class);

    private static final String A_IBAN = "IBAN/BBAN";
    private static final String T_AMOUNT = "BEDRAG";
    private static final String T_DESCRIPTION_1 = "OMSCHRIJVING-1";
    private static final String T_DESCRIPTION_2 = "OMSCHRIJVING-2";
    private static final String T_DESCRIPTION_3 = "OMSCHRIJVING-3";
    private static final String T_OTHER_IBAN = "TEGENREKENING IBAN/BBAN";
    private static final String T_OTHER_NAME = "NAAM TEGENPARTIJ";
    private static final String T_DATE = "DATUM";

    private static final DateTimeFormatter DATUM_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private TransactionDao transactionDao = new TransactionDao();

    @Override
    public void accept(List<CSVRecord> csvRecords)
    {
        csvRecords.stream()
            .map(this::mapCSVRecordToTransaction)
            .peek(transactionDao::insert)
            .forEach(t -> logger.debug("Transaction added: " + t));
    }

    private Transaction mapCSVRecordToTransaction(CSVRecord csvRecord)
    {
        return new Transaction.Builder()
            .setIban(csvRecord.get(A_IBAN))
            .setAmount(Double.parseDouble(csvRecord.get(T_AMOUNT)
                                              .replace(',', '.')))
            .setIbanOther(csvRecord.get(T_OTHER_IBAN))
            .setNameOther(csvRecord.get(T_OTHER_NAME))
            .setDate(LocalDate.parse(csvRecord.get(T_DATE), DATUM_FORMAT))
            .setDescription(csvRecord.get(T_DESCRIPTION_1) + " " + csvRecord.get(T_DESCRIPTION_2) + " " + csvRecord.get(T_DESCRIPTION_3))
            .build();
    }

//    @Override
//    public boolean hasHeader(List<String> header)
//    {
//        List<String> upperCaseHeader = new ArrayList<>();
//
//        header.forEach(field -> upperCaseHeader.add(field.toUpperCase()));
//
//        List<String> expectedHeader = Arrays.asList(
//            A_IBAN,
//            T_AMOUNT,
//            T_DESCRIPTION_1,
//            T_DESCRIPTION_2,
//            T_DESCRIPTION_3,
//            T_OTHER_IBAN,
//            T_OTHER_NAME,
//            T_DATE
//        );
//
//        return upperCaseHeader.containsAll(expectedHeader);
//    }
}
