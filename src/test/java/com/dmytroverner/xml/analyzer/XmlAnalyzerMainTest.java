package com.dmytroverner.xml.analyzer;

import com.dmytroverner.xml.analyzer.model.SimilarityResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * System level tests
 */
public class XmlAnalyzerMainTest {

    @Test
    public void testApp() {
        findSimilar("src/test/resources/sample-0-origin.html",
                "src/test/resources/sample-1-evil-gemini.html",
                XmlAnalyzerApp.DEFAULT_TARGET_ELEMENT_ID,
                "<a class=\"btn btn-success\" href=\"#check-and-ok\" title=\"Make-Button\" " +
                        "rel=\"done\" onclick=\"javascript:window.okDone(); return false;\"> Make everything OK </a>");
        findSimilar("src/test/resources/sample-0-origin.html",
                "src/test/resources/sample-2-container-and-clone.html",
                XmlAnalyzerApp.DEFAULT_TARGET_ELEMENT_ID,
                "<a class=\"btn test-link-ok\" href=\"#ok\" title=\"Make-Button\" rel=\"next\"" +
                        " onclick=\"javascript:window.okComplete(); return false;\"> Make everything OK </a>");
        findSimilar("src/test/resources/sample-0-origin.html",
                "src/test/resources/sample-3-the-escape.html",
                XmlAnalyzerApp.DEFAULT_TARGET_ELEMENT_ID,
                "<a class=\"btn btn-success\" href=\"#ok\" title=\"Do-Link\" rel=\"next\"" +
                        " onclick=\"javascript:window.okDone(); return false;\"> Do anything perfect </a>");
        findSimilar("src/test/resources/sample-0-origin.html",
                "src/test/resources/sample-4-the-mash.html",
                XmlAnalyzerApp.DEFAULT_TARGET_ELEMENT_ID,
                "<a class=\"btn btn-success\" href=\"#ok\" title=\"Make-Button\" rel=\"next\"" +
                        " onclick=\"javascript:window.okFinalize(); return false;\"> Do all GREAT </a>");
    }

    private void findSimilar(String originalDocument, String comparableDocument, String originalElementId,
                             String expectedSimilarElement) {
        SimilarityResult bestCandidate = XmlAnalyzerApp
                .getBestCandidate(originalDocument, comparableDocument, originalElementId);

        assertEquals(expectedSimilarElement, bestCandidate.getElement().toString());
    }
}
