package CodeAlpha_AIChatbot;

import java.util.*;

public class NLPProcessor {

    public static List<String> tokenize(String input) {
        input = input.toLowerCase().replaceAll("[^a-zA-Z ]", "");
        return Arrays.asList(input.split("\\s+"));
    }

    public static double similarity(List<String> inputTokens, List<String> faqTokens) {
        int match = 0;
        for (String word : inputTokens) {
            if (faqTokens.contains(word)) {
                match++;
            }
        }
        return (double) match / Math.max(inputTokens.size(), faqTokens.size());
    }
}