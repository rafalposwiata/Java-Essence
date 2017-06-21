package io.github.rafalposwiata.java_essence.stream.operation;

import io.github.rafalposwiata.java_essence.model.Trader;
import io.github.rafalposwiata.java_essence.model.Transaction;
import org.junit.Test;

import java.util.List;

import static io.github.rafalposwiata.java_essence.data.Traders.*;
import static io.github.rafalposwiata.java_essence.data.Transactions.*;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rafał Poświata.
 */
public class TransactionsExerciseTest {

    @Test
    public void transactionsFrom2011SortedByValueAscending() {
        List<Transaction> transactionsFrom2011SortedByValue = TRANSACTIONS
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        List<Transaction> expectedTransactionsFrom2011SortedByValue = asList(BRIAN_2011_300, RAOUL_2011_400);

        assertEquals(expectedTransactionsFrom2011SortedByValue, transactionsFrom2011SortedByValue);
    }

    @Test
    public void uniqueCitiesWhereTheTradersWork() {
        List<String> uniqueCities = TRANSACTIONS
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        List<String> expectedUniqueCities = asList("Cambridge", "Milan");

        assertEquals(expectedUniqueCities, uniqueCities);
    }

    @Test
    public void tradersFromCambridgeSortedByName() {
        List<Trader> tradersFromCambridgeSortedByName = TRANSACTIONS
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        List<Trader> expectedTradersFromCambridgeSortedByName = asList(ALAN, BRIAN, RAOUL);

        assertEquals(expectedTradersFromCambridgeSortedByName, tradersFromCambridgeSortedByName);
    }

    @Test
    public void stringOfTradersNamesSortedAlphabetically() {
        String stringOfTradersNamesSortedAlphabetically = TRANSACTIONS
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());

        String expectedStringOfTradersNamesSortedAlphabetically = "AlanBrianMarioRaoul";

        assertEquals(expectedStringOfTradersNamesSortedAlphabetically, stringOfTradersNamesSortedAlphabetically);
    }

    @Test
    public void isAnyTraderBasedInMilan() {
        boolean isAnyTraderBasedInMilan = TRANSACTIONS
                .stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));

        assertTrue(isAnyTraderBasedInMilan);
    }

    @Test
    public void highestValueOfAllTransactions() {
        int highestValueOfAllTransactions = TRANSACTIONS
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .orElse(0);

        int expectedHighestValueOfAllTransactions = 1000;

        assertEquals(expectedHighestValueOfAllTransactions, highestValueOfAllTransactions);
    }

    @Test
    public void transactionWithTheSmallestValue() {
        Transaction transactionWithTheSmallestValue = TRANSACTIONS
                .stream()
                .min(comparing(Transaction::getValue))
                .orElse(null);

        Transaction expectedTransactionWithTheSmallestValue = BRIAN_2011_300;

        assertEquals(expectedTransactionWithTheSmallestValue, transactionWithTheSmallestValue);
    }
}
