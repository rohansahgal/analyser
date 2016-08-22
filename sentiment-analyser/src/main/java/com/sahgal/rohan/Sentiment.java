package com.sahgal.rohan;

import com.google.gson.Gson;

/**
 * Created by rohansahgal on 8/22/16.
 */
public class Sentiment {
    private String polarity;
    private String subjectivity;
    private String text;
    private String polarity_confidence;
    private String subjectivity_confidence;

    public String getPolarity() {
        return polarity;
    }

    public Sentiment setPolarity(String a_polarity) {
        polarity = a_polarity;
        return this;
    }

    public String getSubjectivity() {
        return subjectivity;
    }

    public Sentiment setSubjectivity(String a_subjectivity) {
        subjectivity = a_subjectivity;
        return this;
    }

    public String getText() {
        return text;
    }

    public Sentiment setText(String a_text) {
        text = a_text;
        return this;
    }

    public String getPolarity_confidence() {
        return polarity_confidence;
    }

    public Sentiment setPolarity_confidence(String a_polarity_confidence) {
        polarity_confidence = a_polarity_confidence;
        return this;
    }

    public String getSubjectivity_confidence() {
        return subjectivity_confidence;
    }

    public Sentiment setSubjectivity_confidence(String a_subjectivity_confidence) {
        subjectivity_confidence = a_subjectivity_confidence;
        return this;
    }

    public static Sentiment parse(String a_sentimentString, Gson a_gson) {
        return a_gson.fromJson(a_sentimentString, Sentiment.class);
    }

    public static void main(String[] args) {
        String s = "{\n" +
                " \"polarity\": \"positive\",\n" +
                " \"subjectivity\": \"subjective\",\n" +
                " \"text\": \"John is a very good football player\",\n" +
                " \"polarity_confidence\": 0.9824022650718689,\n" +
                " \"subjectivity_confidence\": 0.9963778207617525\n" +
                " }";
        Sentiment sentiment = new Gson().fromJson(s, Sentiment.class);
        System.out.println(sentiment);
  }

}
