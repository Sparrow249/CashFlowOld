package nl.sparrow.cashflow.logic.transaction;

import nl.sparrow.cashflow.logic.category.Category;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public final class Transaction
{
    private final String iban;
    private final Double amount;
    private final LocalDate date;
    private final String ibanOther;
    private final String nameOther;
    private final Category category;
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

    public String getIban()
    {
        return iban;
    }

    public Double getAmount()
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

    public Category getCategory()
    {
        return category;
    }

    public Transaction changeCategory(Category category)
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
        private String iban;
        private String ibanOther;
        private Double amount;
        private LocalDate date;
        private String nameOther;
        private Category category;
        private String description;

        public Builder() {}

        /**
         * Transaction Builder constructor to start from a copy of another Transaction
         *
         * @param transaction Transaction to copy
         */
        public Builder(Transaction transaction)
        {
            this.iban = transaction.iban;
            this.amount = transaction.amount;
            this.date = transaction.date;
            this.ibanOther = transaction.ibanOther;
            this.nameOther = transaction.nameOther;
            this.category = transaction.category;
            this.description = transaction.description;
        }

        public Builder setIban(String iban)
        {
            this.iban = iban;
            return this;
        }

        public Builder setAmount(Double amount)
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

        public Builder setCategory(Category category)
        {
            this.category = category;
            return this;
        }


        public Builder setDescription(String description)
        {
            this.description = description;
            return this;
        }

        /**
         * builds a Transaction from this builder object
         *
         * @return new Transaction
         * @throws NullPointerException if a required field is null
         */
        public Transaction build() throws NullPointerException
        {
            requireNonNull(iban);
            requireNonNull(ibanOther);
            requireNonNull(date);
            requireNonNull(amount);

            if (category == null)
            {
                category = Category.OTHER;
            }

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
