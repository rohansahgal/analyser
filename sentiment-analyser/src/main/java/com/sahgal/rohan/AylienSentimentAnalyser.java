package com.sahgal.rohan;

import com.google.gson.Gson;
import com.sahgal.rohan.io.RemoteHelper;
import com.sahgal.rohan.url.ExtractedPage;
import com.sahgal.rohan.url.UrlExtractor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by rohansahgal on 8/22/16.
 */
@Service
public class AylienSentimentAnalyser implements SentimentAnalyser {

    private String m_apiKey = "77ac5bad48565e0bd82128721bf76e35";
    private String m_appId = "433f4463";
    private RemoteHelper m_remoteHelper;
    private UrlExtractor m_urlExtractor;
    private Gson m_gson;
    private static final String s_apiUrl = "https://api.aylien.com/api/v1/sentiment";

    @Autowired
    public AylienSentimentAnalyser(RemoteHelper a_remoteHelper, UrlExtractor a_urlExtractor, Gson a_gson) {
        m_remoteHelper = a_remoteHelper;
        m_urlExtractor = a_urlExtractor;
        m_gson = a_gson;
    }

    @Override
    public Sentiment analyse(String a_url) throws IOException {
        String getBody = m_remoteHelper.executeGetRequestAndGetResponseAsString(a_url);
        ExtractedPage extractedPage = m_urlExtractor.extractUrl(getBody);

        HttpPost httpPost = new HttpPost(s_apiUrl);
        httpPost.addHeader("X-AYLIEN-TextAPI-Application-Key", m_apiKey);
        httpPost.addHeader("X-AYLIEN-TextAPI-Application-ID", m_appId);
        ArrayList<NameValuePair> postParameters = new ArrayList<>();
        postParameters.add(new BasicNameValuePair("text", extractedPage.toString()));
        httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
        String body = m_remoteHelper.executeRequestAndGetBody(s_apiUrl, httpPost);
        return Sentiment.parse(body, m_gson);
    }
}
