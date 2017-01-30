package io.github.rafalposwiata.java_essence.data;

import com.google.common.collect.ImmutableList;
import io.github.rafalposwiata.java_essence.model.Transaction;

import java.util.List;

import static io.github.rafalposwiata.java_essence.data.Traders.*;

/**
 * @author Rafał Poświata.
 */
public class Transactions {

    private Transactions() {
    }

    public static final Transaction BRIAN_2011_300 = new Transaction(BRIAN, 2011, 300);

    public static final Transaction RAOUL_2012_1000 = new Transaction(RAOUL, 2012, 1000);

    public static final Transaction RAOUL_2011_400 = new Transaction(RAOUL, 2011, 400);

    public static final Transaction MARIO_2012_710 = new Transaction(MARIO, 2012, 710);

    public static final Transaction MARIO_2012_700 = new Transaction(MARIO, 2012, 700);

    public static final Transaction ALAN_2012_950 = new Transaction(ALAN, 2012, 950);

    public static final List<Transaction> TRANSACTIONS = ImmutableList.of(
            BRIAN_2011_300,
            RAOUL_2012_1000,
            RAOUL_2011_400,
            MARIO_2012_710,
            MARIO_2012_700,
            ALAN_2012_950
    );
}
