package com.sahgal.rohan.url;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by rohansahgal on 8/22/16.
 */
public class ExtractedPage {
    private String m_title;
    private String m_description;
    private String m_author;
    private String m_date;

    public String getTitle() {
        return m_title;
    }

    public ExtractedPage setTitle(String a_title) {
        m_title = a_title;
        return this;
    }

    public String getDescription() {
        return m_description;
    }

    public ExtractedPage setDescription(String a_description) {
        m_description = a_description;
        return this;
    }

    public String getAuthor() {
        return m_author;
    }

    public ExtractedPage setAuthor(String a_author) {
        m_author = a_author;
        return this;
    }

    public String getDate() {
        return m_date;
    }

    public ExtractedPage setDate(String a_date) {
        m_date = a_date;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(m_title != null) {
            stringBuilder.append(m_title).append(" ");
        }
        if(m_description != null) {
            stringBuilder.append(m_description).append(" ");
        }
        if(m_author != null) {
            stringBuilder.append(m_author).append(" ");
        }
        if(m_date != null) {
            stringBuilder.append(m_date).append(" ");
        }
        return stringBuilder.toString();
    }
}
