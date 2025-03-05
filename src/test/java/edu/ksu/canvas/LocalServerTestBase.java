package edu.ksu.canvas;

import edu.ksu.canvas.net.SimpleRestClientUTest;
import edu.ksu.canvas.util.JsonTestUtil;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.testing.classic.ClassicTestServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import java.net.InetAddress;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Base test class for classes that need a LocalTestServer to simulate
 * network traffic
 */
public class LocalServerTestBase {

    private final ClassicTestServer server = new ClassicTestServer();
    protected String baseUrl;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
        server.shutdown(CloseMode.GRACEFUL);
    }

    protected void registerUrlResponse(String url, String sampleJsonFileName, Integer statusCode, Map<String, String> headers) throws Exception {
        String jsonContent = JsonTestUtil.loadJson(sampleJsonFileName, SimpleRestClientUTest.class);
        server.registerHandler(url, (request, response, context) -> {
            response.setCode(statusCode);
            for(String key : headers.keySet()) {
                String value = headers.get(key);
                response.addHeader(key, value);
            }
            response.setEntity(new StringEntity(jsonContent));
        });
        assertDoesNotThrow(() -> server.start());
        InetAddress address = server.getInetAddress();
        baseUrl = "http://" + address.getHostAddress() + ":" + server.getPort();
    }

}
