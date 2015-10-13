package edu.ksu.canvas.impl;

import edu.ksu.canvas.interfaces.QuizReader;
import edu.ksu.canvas.interfaces.QuizWriter;
import edu.ksu.canvas.net.RestClient;

import org.apache.log4j.Logger;

public class QuizImpl extends  BaseImpl implements QuizReader, QuizWriter {
    private static final Logger LOG = Logger.getLogger(QuizReader.class);

    public QuizImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient);
    }
}
