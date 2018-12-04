package com.dmytroverner.xml.analyzer.strategy;

import org.jsoup.nodes.Element;

import java.util.List;

public class TextSimilarityStrategy implements SimilarityStrategy {

    @Override
    public int compare(Element original, Element comparable, List<String> decisionLog) {
        if (original.hasText() && comparable.hasText()) {
            String originalText = original.text();
            String comparableText = comparable.text();
            if (originalText.equals(comparableText)) {
                decisionLog.add("Original element's text matched with the comparable one's text.");
                return 3;
            }
            else if (originalText.toLowerCase().equals(comparableText.toLowerCase())) {
                decisionLog.add("Original element's text lowercase matched with the comparable one's text.");
                return 2;
            }
            else if (originalText.toLowerCase().contains(comparableText.toLowerCase()) ||
                comparableText.toLowerCase().contains(originalText.toLowerCase())) {
                decisionLog.add("Original element's text contains the comparable one's text or vice versa.");
                return 1;
            }
        }
        return 0;
    }
}
