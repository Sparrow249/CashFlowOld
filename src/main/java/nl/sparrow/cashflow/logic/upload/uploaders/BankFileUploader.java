package nl.sparrow.cashflow.logic.upload.uploaders;

import nl.sparrow.cashflow.logic.upload.Bank;

import java.io.File;

public interface BankFileUploader
{
    void upload(File file, Bank bank);
}
