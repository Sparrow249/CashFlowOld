package nl.sparrow.cashflow.logic.category;

import com.sun.istack.internal.NotNull;

import static java.util.Objects.requireNonNull;

public class Category
{
    public static final Category OTHER = new Category("Other");

    private final String value;

    public Category(@NotNull String value)
    {
        this.value = requireNonNull(value);
    }
    

    public String getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Category){
            Category other = (Category) obj;
            if(this.value.equals((other.value)))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        return value.hashCode();
    }

    @Override
    public String toString(){
        return "[Category] value="+value;
    }
}
