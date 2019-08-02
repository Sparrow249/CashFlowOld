package nl.sparrow.cashflow.logic.transaction;

import com.sun.istack.internal.NotNull;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public final class Transaction
{
    private final String iban;
    private final double amount;
    private final LocalDate date;
    private final String ibanOther;
    private final String nameOther;
    private final String category;
    private final String description;


    private Transaction(Builder builder)
    {
        this.iban = builder.iban;
        this.amount = builder.amount;
        this.date = builder.date;
        this.ibanOther = builder.ibanOther;
        this.nameOther = builder.nameOther;
        this.category = builder.category;
        this.description = builder.description;
    }


    public double getAmount()
    {
        return amount;
    }


    public String getDescription()
    {
        return description;
    }


    public LocalDate getDate()
    {
        return date;
    }


    public String getIbanOther()
    {
        return ibanOther;
    }


    public String getNameOther()
    {
        return nameOther;
    }

    public String getCategory()
    {
        return category;
    }

    public Transaction changeCategory(String category)
    {
        return new Builder(this)
            .setCategory(category)
            .build();
    }

    @Override
    public String toString()
    {
        return "[Transaction]"
            + " iban=" + iban
            + " amount=" + amount
            + " date=" + date
            + " otherIban=" + ibanOther
            + " othername=" + nameOther
            + " category=" + category
            + " description=" + description;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Transaction)
        {
            Transaction other = (Transaction) obj;
            if (iban.equals(other.iban)
                && date.equals(other.date)
                && amount == other.amount
                && ibanOther.equals(other.ibanOther))
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public int hashCode()
    {
        return ibanOther.hashCode() + 3 * date.hashCode();
    }


    public static class Builder
    {
        @NotNull
        private String iban;
        private double amount;
        @NotNull
        private LocalDate date;
        @NotNull
        private String ibanOther;
        private String nameOther;
        private String category;
        private String description;

        public Builder()
        {
        }

        public Builder(Transaction t)
        {
            this.iban = t.iban;
            this.amount = t.amount;
            this.date = t.date;
            this.ibanOther = t.ibanOther;
            this.nameOther = t.nameOther;
            this.category = t.category;
            this.description = t.description;
        }

        public Builder setIban(String iban)
        {
            this.iban = iban;
            return this;
        }

        public Builder setAmount(double amount)
        {
            this.amount = amount;
            return this;
        }

        public Builder setDate(LocalDate date)
        {
            this.date = date;
            return this;
        }

        public Builder setIbanOther(String ibanOther)
        {
            this.ibanOther = ibanOther;
            return this;
        }

        public Builder setNameOther(String name)
        {
            this.nameOther = name;
            return this;
        }

        public Builder setCategory(String category)
        {
            this.category = category;
            return this;
        }


        public Builder setDescription(String description)
        {
            this.description = description;
            return this;
        }


        public Transaction build()
        {
            requireNonNull(iban);
            requireNonNull(ibanOther);
            requireNonNull(date);

            return new Transaction(this);
        }
    }

    public static class InstanceField
    {
        public static final String iban = "Iban";
        public static final String amount = "Amount";
        public static final String date = "Date";
        public static final String ibanOther = "IbanOther";
        public static final String nameOther = "NameOther";
        public static final String category = "Category";
        public static final String description = "Description";
    }
}
