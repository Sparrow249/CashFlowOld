package nl.sparrow.cashflow.logic.models;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Dao<T>
{
    List<T> fetch(Predicate<T> filter);

    List<T> fetchAll();

    void insert(T t);

    void delete(T t);
}
