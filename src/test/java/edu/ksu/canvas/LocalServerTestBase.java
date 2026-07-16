package edu.ksu.canvas;

import edu.ksu.canvas.net.SimpleRestClientUTest;
import edu.ksu.canvas.util.JsonTestUtil;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.testing.classic.ClassicTestServer;
import org.junit.After;
import org.junit.Before;

import java.util.Map;

/**
 * Base test class for classes that need a local test server to simulate network traffic.
 */
public class LocalServerTestBase {
    protected ClassicTestServer server;
    protected String baseUrl;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        if (server != null) {
            server.shutdown(CloseMode.GRACEFUL);
        }
    }

    protected void registerUrlResponse(String url, String sampleJsonFileName, Integer statusCode, Map<String, String> headers) throws Exception {
        String jsonContent = JsonTestUtil.loadJson(sampleJsonFileName, SimpleRestClientUTest.class);
        server = new ClassicTestServer();
        server.register(url, (request, response, context) -> {
            response.setCode(statusCode);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                response.addHeader(entry.getKey(), entry.getValue());
            }
            response.setEntity(new StringEntity(jsonContent));
        });
        server.start();
        baseUrl = "http://localhost:" + server.getPort();
    }

}
