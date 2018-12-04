package com.dmytroverner.xml.analyzer.analyzer;

import com.dmytroverner.xml.analyzer.model.SimilarityResult;
import com.dmytroverner.xml.analyzer.strategy.SimilarityStrategy;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;

public class ElementAnalyzer {

    private Element original;
    private List<Element> candidates;

    public ElementAnalyzer(Element original, List<Element> candidates) {
        this.original = original;
        this.candidates = new ArrayList<>(candidates);
    }

    public SimilarityResult getBestCandidate(List<SimilarityStrategy> strategies) {
        List<SimilarityResult> weightedCandidates = new ArrayList<>(candidates.size());
        List<String> decisionLog = new ArrayList<>();

        candidates.forEach(candidate -> {
                    int score = strategies
                            .stream()
                            .mapToInt(strategy -> strategy.compare(original, candidate, decisionLog))
                            .sum();

                    weightedCandidates.add(new SimilarityResult(candidate, score, decisionLog));
                });
        weightedCandidates.sort(comparing(SimilarityResult::getScore).reversed());
        return weightedCandidates.get(0);
    }
}
