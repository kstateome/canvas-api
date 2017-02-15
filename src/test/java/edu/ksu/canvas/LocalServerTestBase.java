package edu.ksu.canvas;

import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.apache.http.localserver.LocalTestServer;
import org.junit.After;
import org.junit.Before;

import edu.ksu.canvas.net.SimpleRestClientUTest;
import edu.ksu.canvas.util.JsonTestUtil;

/**
 * Base test class for classes that need a LocalTestServer to simulate
 * network traffic
 */
public class LocalServerTestBase {
    private LocalTestServer server = new LocalTestServer(null, null);
    protected String baseUrl;

    @Before
    public void setUp() throws Exception {
        server.start();
        baseUrl = "http:/" + server.getServiceAddress();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    protected void registerUrlResponse(String url, String sampleJsonFileName, Integer statusCode, Map<String, String> headers) {
        String jsonContent = JsonTestUtil.loadJson(sampleJsonFileName, SimpleRestClientUTest.class);
        server.register(url, (request, response, context) -> {
            response.setStatusCode(statusCode);
            for(String key : headers.keySet()) {
                String value = headers.get(key);
                response.addHeader(key, value);
            }
            response.setEntity(new StringEntity(jsonContent));
        });
    }

}
