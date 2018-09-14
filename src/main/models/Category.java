package main.models;

import main.services.TransactionFilterService;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private static List<Category> categories = new ArrayList<>();

    private String name;

    public Category(){
        this("");
    }

    public Category(String name){
        this.name = name;
        if(!categories.contains(this)){
            categories.add(this);
        }
    }

    public static void removeCategory(Category category){
        //set the catergory of every transaction with this category to null
        for(Account account: Account.getAccounts()) {
            List<Transaction> filteredTransactions = TransactionFilterService.filter(account.getTransactions(), category);
            for(Transaction transaction: filteredTransactions){
                transaction.setCategory(null);
            }
        }

        //remove from categories List
        categories.remove(category);
    }

    public static List<Category> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Category){
            return(((Category) other).getName().equals(this.getName()));
        }
        else{
            return super.equals(other);
        }
    }

}
