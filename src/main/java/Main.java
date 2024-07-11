import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final int TEXTS_SIZE = 100_000;
    private static final int LENGTH = 3;
    private static final String LETTERS = "abc";
    private static final AtomicInteger COUNTER_FOR_LENGTH_THREE = new AtomicInteger(0);
    private static final AtomicInteger COUNTER_FOR_LENGTH_FOUR = new AtomicInteger(0);
    private static final AtomicInteger COUNTER_FOR_LENGTH_FIVE = new AtomicInteger(0);


    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[TEXTS_SIZE];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText(LETTERS, LENGTH + random.nextInt(3));
        }
        Thread thread = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (texts[i].length() == 3) {
                    if (isPalindrome(texts[i])) {
                        COUNTER_FOR_LENGTH_THREE.getAndIncrement();
                    }
                } else if (texts[i].length() == 4) {
                    if (isPalindrome(texts[i])) {
                        COUNTER_FOR_LENGTH_FOUR.getAndIncrement();
                    }
                } else if (texts[i].length() == 5) {
                    if (isPalindrome(texts[i])) {
                        COUNTER_FOR_LENGTH_FIVE.getAndIncrement();
                    }
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (texts[i].length() == 3) {
                    if (identicalLetters(texts[i])) {
                        COUNTER_FOR_LENGTH_THREE.getAndIncrement();
                    }
                } else if (texts[i].length() == 4) {
                    if (identicalLetters(texts[i])) {
                        COUNTER_FOR_LENGTH_FOUR.getAndIncrement();
                    }
                } else if (texts[i].length() == 5) {
                    if (identicalLetters(texts[i])) {
                        COUNTER_FOR_LENGTH_FIVE.getAndIncrement();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                if (texts[i].length() == 3) {
                    if (lettersAscending(texts[i])) {
                        COUNTER_FOR_LENGTH_THREE.getAndIncrement();
                    }
                } else if (texts[i].length() == 4) {
                    if (lettersAscending(texts[i])) {
                        COUNTER_FOR_LENGTH_FOUR.getAndIncrement();
                    }
                } else if (texts[i].length() == 5) {
                    if (lettersAscending(texts[i])) {
                        COUNTER_FOR_LENGTH_FIVE.getAndIncrement();
                    }
                }
            }
        });

        thread.start();
        thread1.start();
        thread2.start();

        System.out.println("Красивых слов с длиной 3: " + COUNTER_FOR_LENGTH_THREE);
        System.out.println("Красивых слов с длиной 4: " + COUNTER_FOR_LENGTH_FOUR);
        System.out.println("Красивых слов с длиной 5: " + COUNTER_FOR_LENGTH_FIVE);
    }

    public static boolean isPalindrome(String word) {
        int length = word.length();
        for (int i = 0; i < (length / 2); i++) {
            if (word.charAt(i) != word.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean identicalLetters(String word) {
        char firstLetter = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            if (firstLetter != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean lettersAscending(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        StringBuilder builder = new StringBuilder();
        builder.append(chars);
        return word.equals(builder.toString());
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
