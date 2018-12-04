package com.dmytroverner.xml.analyzer.io;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class FileParser {

    private final String CHARSET_NAME = "UTF-8";

    public Document parse(File file) {
        try {
            return Jsoup.parse(file, CHARSET_NAME, file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Can't' parse the provided file " + e);
        }
    }
}
