package com.sahgal.rohan;

import com.sahgal.rohan.url.ExtractedPage;

import java.io.IOException;

/**
 * Created by rohansahgal on 8/22/16.
 */
public interface SentimentAnalyser {
    Sentiment analyse(String a_url) throws IOException;
}
