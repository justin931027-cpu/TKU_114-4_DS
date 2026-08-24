import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordIndexSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> wordCount = new HashMap<>();
        Set<String> uniqueWords = new HashSet<>();

        System.out.println("請輸入一段文章：");
        String text = sc.nextLine();

        text = text.toLowerCase();
        text = text.replaceAll("[.,!?;:\"'()\\[\\]{}]", "");

        String[] words = text.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                uniqueWords.add(word);
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        System.out.println();
        System.out.println("單字出現次數：");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        System.out.println();
        System.out.println("不重複單字：");
        System.out.println(uniqueWords);

        System.out.println();
        System.out.println("出現至少兩次的單字：");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() >= 2) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }

        sc.close();
    }
}