package com.sahgal.rohan.io;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by rohansahgal on 8/22/16.
 */
@Component
public class RemoteHelper {
    private CloseableHttpClient m_httpClient;

    @Autowired
    public RemoteHelper(CloseableHttpClient a_httpClient) {
        m_httpClient = checkNotNull(a_httpClient);
    }

    public String executeGetRequestAndGetResponseAsString(String a_url) throws IOException {
        checkArgument(!StringUtils.isBlank(a_url));
        HttpRequestBase httpRequest = new HttpGet(a_url);
        return executeRequestAndGetBody(a_url, httpRequest);
    }

    public String executePostRequestAndGetResponseAsString(String a_url) throws IOException {
        checkArgument(!StringUtils.isBlank(a_url));
        HttpRequestBase httpRequest = new HttpPost(a_url);
        return executeRequestAndGetBody(a_url, httpRequest);
    }

    public String executeRequestAndGetBody(String a_url, HttpRequestBase a_httpRequest) throws IOException {
        checkArgument(!StringUtils.isBlank(a_url));
        checkNotNull(a_httpRequest);

        HttpEntity entity = null;
        try (CloseableHttpResponse response = m_httpClient.execute(a_httpRequest)){
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode != 200) {
                throw new IOException("Request to " + a_url + " failed with status " + statusCode);
            }
            entity = response.getEntity();
            return IOUtils.toString(entity.getContent());
        } finally {
            if(entity != null) {
                EntityUtils.consume(entity);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient defaultHttpClient = HttpClients.createDefault();
        RemoteHelper remoteHelper = new RemoteHelper(defaultHttpClient);
        String response = remoteHelper.executeGetRequestAndGetResponseAsString("http://www.google.com");
        System.out.println(response);
    }
}
