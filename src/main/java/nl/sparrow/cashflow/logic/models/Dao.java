package nl.sparrow.cashflow.logic.models;

import java.util.List;
import java.util.function.Predicate;

public interface Dao<T>
{
    List<T> fetchAll(Predicate<T> filter);

    List<T> fetchAll();

    void insert(T t);

    void delete(T t);
}
