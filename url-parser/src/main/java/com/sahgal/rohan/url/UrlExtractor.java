package com.sahgal.rohan.url;

import com.sahgal.rohan.io.RemoteHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rohansahgal on 8/22/16.
 */
@Component
public class UrlExtractor {
    private RemoteHelper m_remoteHelper;

    @Autowired
    public UrlExtractor(RemoteHelper a_remoteHelper) {
        m_remoteHelper = checkNotNull(a_remoteHelper);
    }

    public ExtractedPage extractUrl(String a_body) throws IOException {
        checkArgument(!StringUtils.isBlank(a_body));
        Document document = Jsoup.parse(a_body);
        ExtractedPage extractedPage = new ExtractedPage();
        extractedPage.setTitle(document.title());

        for(Element metadata : document.select("meta")) {
            String attrName = metadata.attr("name");
            String content = metadata.attr("content");

            if (isAttribute(attrName, "author")) {
                extractedPage.setAuthor(content);
            } else if (isAttribute(attrName, "summary") || isAttribute(attrName, "description")) {
                extractedPage.setDescription(content);
            } else if (isAttribute(attrName, "last-modified")) {
                extractedPage.setDate(content);
            }
        }

        return extractedPage;
    }

    private boolean isAttribute(String attrName, String expectedName) {
        return attrName.toLowerCase().startsWith(expectedName);
    }

    public static void main(String[] args) throws IOException {
        UrlExtractor urlExtractor = new UrlExtractor(new RemoteHelper(HttpClients.createDefault()));
        urlExtractor.extractUrl("https://en.wikipedia.org/wiki/Olympic_Games");
    }


}
