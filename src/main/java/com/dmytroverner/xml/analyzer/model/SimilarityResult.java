package com.dmytroverner.xml.analyzer.model;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class SimilarityResult {

    private Element element;
    private int score;
    private List<String> decisionLog;

    public SimilarityResult(Element element, int score, List<String> decisionLog) {
        this.element = element;
        this.score = score;
        this.decisionLog = new ArrayList<>(decisionLog);
    }

    public Element getElement() {
        return element;
    }

    public int getScore() {
        return score;
    }

    public List<String> getDecisionLog() {
        return new ArrayList<>(decisionLog);
    }
}
