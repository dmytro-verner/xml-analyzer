package com.dmytroverner.xml.analyzer.dom;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class DomParser {

    private final String DELIMITER = ">";

    public Element getElementById(String id, Document source) {
        return Optional.ofNullable(source.body().getElementById(id))
                .orElseThrow(() -> new RuntimeException(
                        "No element with id" + id + " for source: " + source + " was found."));
    }

    public List<Element> getElementsByTag(String tag, Document source) {
        return Optional.ofNullable(source.body().getElementsByTag(tag))
                .orElseThrow(() -> new RuntimeException(
                        "No element with tag" + tag + " for source: " + source + " was found."));
    }

    public String getPathOf(Element element) {
        List<String> originPath = element.parents()
                .stream()
                .map(DomParser::formatPath)
                .collect(toList());
        Collections.reverse(originPath);
        originPath.add(formatPath(element));
        return String.join(DELIMITER, originPath);
    }

    private static String formatPath(Element element) {
        return String.format("%s[%s]", element.tagName(), element.elementSiblingIndex());
    }
}
