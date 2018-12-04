package com.dmytroverner.xml.analyzer.strategy;

import org.jsoup.nodes.Element;

import java.util.List;

public interface SimilarityStrategy {

    int compare(Element original, Element comparable, List<String> decisionLog);
}
