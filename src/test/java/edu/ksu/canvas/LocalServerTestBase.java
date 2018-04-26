package edu.ksu.canvas;

import edu.ksu.canvas.net.SimpleRestClientUTest;
import edu.ksu.canvas.util.JsonTestUtil;
import org.apache.http.HttpHost;
import org.apache.http.entity.StringEntity;
import org.junit.After;
import org.junit.Before;

import java.util.Map;

/**
 * Base test class for classes that need a LocalTestServer to simulate
 * network traffic
 */
public class LocalServerTestBase extends org.apache.http.localserver.LocalServerTestBase{
    protected String baseUrl;
    protected HttpHost httpHost;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
        super.shutDown();
    }

    protected void registerUrlResponse(String url, String sampleJsonFileName, Integer statusCode, Map<String, String> headers) throws Exception {
        String jsonContent = JsonTestUtil.loadJson(sampleJsonFileName, SimpleRestClientUTest.class);
        serverBootstrap.registerHandler(url, (request, response, context) -> {
            response.setStatusCode(statusCode);
            for(String key : headers.keySet()) {
                String value = headers.get(key);
                response.addHeader(key, value);
            }
            response.setEntity(new StringEntity(jsonContent));
        });
        httpHost = super.start();
        baseUrl = httpHost.toURI();
    }

}
