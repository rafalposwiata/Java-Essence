package io.github.rafalposwiata.java_essence.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Rafał Poświata.
 */
public class WordSpliterator implements Spliterator<Character> {

    private static final int MIN_SIZE_TO_SPLIT = 10;

    private final String text;
    private int currentCharIdx = 0;

    public WordSpliterator(String text) {
        this.text = text;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(text.charAt(currentCharIdx++));
        return currentCharIdx < text.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = text.length() - currentCharIdx;
        if (currentSize < MIN_SIZE_TO_SPLIT) return null;

        for (int splitPos = currentSize / 2 + currentCharIdx; splitPos < text.length(); splitPos++) {
            if (Character.isWhitespace(text.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordSpliterator(text.substring(currentCharIdx, splitPos));
                currentCharIdx = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return text.length() - currentCharIdx;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
