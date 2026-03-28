package CodeAlpha_AIChatbot;

import java.io.*;
import java.util.*;

public class FAQTrainer {

    private Map<String, String> faqMap = new HashMap<>();

    public FAQTrainer(String filePath) {
        loadFAQs(filePath);
    }

    private void loadFAQs(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    faqMap.put(parts[0].toLowerCase(), parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading FAQ file.");
        }
    }

    public Map<String, String> getFAQMap() {
        return faqMap;
    }
}