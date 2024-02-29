package com.example.allknuauth.global.scraper;

import org.jsoup.nodes.Document;

public interface Scraper<T> {
    T scrape(Document document) throws Exception;
}