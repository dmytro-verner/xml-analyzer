package com.dmytroverner.xml.analyzer.strategy;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class AttributesSimilarityStrategy implements SimilarityStrategy {

    private Map<String, String> getAttributes(Element element) {
        return element.attributes()
                .asList()
                .stream()
                .collect(toMap(Attribute::getKey, Attribute::getValue));
    }

    @Override
    public int compare(Element original, Element comparable, List<String> decisionLog) {
        Map<String, String> originalAttributes = getAttributes(original);
        Map<String, String> comparableAttributes = getAttributes(comparable);

        return originalAttributes
                .keySet()
                .stream()
                .filter(comparableAttributes::containsKey)
                .mapToInt(key -> {
                    String originalValue = originalAttributes.get(key);
                    String comparableValue = comparableAttributes.get(key);

                    if (originalValue.equals(comparableValue)) {
                        decisionLog.add(String.format("Attribute '%s' with value '%s' matched", key, originalValue));
                        return 2;
                    } else if (originalValue.toLowerCase().trim().equals(comparableValue.toLowerCase().trim())) {
                        decisionLog.add(String.format("Attribute '%s' with value '%s' are lowercase-trim matched",
                                key, originalValue));
                        return 1;
                    }
                    return 0;
                })
                .sum();
    }
}
