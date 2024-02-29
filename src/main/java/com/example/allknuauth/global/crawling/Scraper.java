package com.example.allknuauth.global.crawling;

import java.io.IOException;

public interface Scraper<T> {

    T scrap() throws IOException;
}
