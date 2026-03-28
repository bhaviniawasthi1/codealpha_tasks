package CodeAlpha_AIChatbot;

import java.util.*;

public class ChatEngine {

    private FAQTrainer trainer;

    public ChatEngine(String faqFile) {
        trainer = new FAQTrainer(faqFile);
    }

    public String getResponse(String input) {

        input = input.toLowerCase();
        List<String> inputTokens = NLPProcessor.tokenize(input);

        double bestScore = 0;
        String bestAnswer = "Sorry, I didn't understand that.";

        for (Map.Entry<String, String> entry : trainer.getFAQMap().entrySet()) {
            List<String> faqTokens = NLPProcessor.tokenize(entry.getKey());
            double score = NLPProcessor.similarity(inputTokens, faqTokens);

            if (score > bestScore) {
                bestScore = score;
                bestAnswer = entry.getValue();
            }
        }

        // Threshold for better matching
        if (bestScore < 0.3) {
            return "Can you please rephrase?";
        }

        return bestAnswer;
    }
}