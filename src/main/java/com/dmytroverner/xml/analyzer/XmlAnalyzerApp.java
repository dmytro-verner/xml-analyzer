package com.dmytroverner.xml.analyzer;

import com.dmytroverner.xml.analyzer.analyzer.ElementAnalyzer;
import com.dmytroverner.xml.analyzer.dom.DomParser;
import com.dmytroverner.xml.analyzer.io.ConsoleWriter;
import com.dmytroverner.xml.analyzer.io.FileParser;
import com.dmytroverner.xml.analyzer.io.Writer;
import com.dmytroverner.xml.analyzer.model.SimilarityResult;
import com.dmytroverner.xml.analyzer.strategy.AttributesSimilarityStrategy;
import com.dmytroverner.xml.analyzer.strategy.SimilarityStrategy;
import com.dmytroverner.xml.analyzer.strategy.TextSimilarityStrategy;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class XmlAnalyzerApp {

    static final String DEFAULT_TARGET_ELEMENT_ID = "make-everything-ok-button";

    private static FileParser fileParser = new FileParser();
    private static DomParser domParser = new DomParser();
    private static Writer writer = new ConsoleWriter();

    private static List<SimilarityStrategy> similarityStrategies = Arrays.asList(new AttributesSimilarityStrategy(),
            new TextSimilarityStrategy());

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            throw new IllegalArgumentException(
                    "Program's arguments should be provided as: <input_origin_file_path> <input_other_sample_file_path>");
        }
        final String originalDocumentPath = args[0];
        final String comparableDocumentPath = args[1];

        final String targetElementId;
        if (args.length < 3) {
            targetElementId = DEFAULT_TARGET_ELEMENT_ID;
        } else {
            targetElementId = args[2];
        }

        SimilarityResult bestCandidate = getBestCandidate(originalDocumentPath, comparableDocumentPath,
                targetElementId);
        if (bestCandidate == null)
            return;

        writer.write(domParser.getPathOf(bestCandidate.getElement()));
        writer.write("The decision about potential candidate was made based on:");
        writer.write(String.join("\n", bestCandidate.getDecisionLog()));
        writer.write("Total similarity score is: " + bestCandidate.getScore());
    }

    static SimilarityResult getBestCandidate(String originalDocumentPath, String comparableDocumentPath,
                                             String originalElementId) {
        Document originalDocument = fileParser.parse(new File(originalDocumentPath));
        Document comparableDocument = fileParser.parse(new File(comparableDocumentPath));

        Element originalElement = domParser.getElementById(originalElementId, originalDocument);
        List<Element> candidateElements = domParser.getElementsByTag(originalElement.tagName(), comparableDocument);

        if (candidateElements.isEmpty()) {
            writer.write("The provided document doesn't contain potential candidates");
            return null;
        }

        ElementAnalyzer elementAnalyzer = new ElementAnalyzer(originalElement, candidateElements);
        return elementAnalyzer.getBestCandidate(similarityStrategies);
    }
}
