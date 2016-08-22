package com.sahgal.rohan.analyser.service;

import com.sahgal.rohan.AylienSentimentAnalyser;
import com.sahgal.rohan.Sentiment;
import com.sahgal.rohan.SentimentAnalyser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by rohansahgal on 8/22/16.
 */
@Controller
@EnableAutoConfiguration
public class AnalyseController {
    private SentimentAnalyser m_sentimentAnalyser;

    @Autowired
    public AnalyseController(SentimentAnalyser a_sentimentAnalyser) {
        m_sentimentAnalyser = a_sentimentAnalyser;
    }

    @RequestMapping(value = "/analyse", method = RequestMethod.POST)
    @ResponseBody
    public Sentiment analyse(@RequestParam("url") String a_url,
                             @RequestParam(value = "callback", required = false) String a_callback) throws IOException {
        Sentiment analyse = m_sentimentAnalyser.analyse(a_url);
        return analyse;
    }


}
