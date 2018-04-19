package ru.itis;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Streams
 * <p>
 * 17 04 2018
 *
 * @author Nita
 */
public interface Stream <E> {
    Stream <E> map (Function mapper);
    Stream <E> filter (Predicate predicate);
    Stream <E> sorted (Comparator <? super E> comparator);
}
