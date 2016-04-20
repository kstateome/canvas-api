package edu.ksu.canvas.config;

import edu.ksu.canvas.impl.GsonResponseParser;
import edu.ksu.canvas.impl.RestCanvasMessenger;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.interfaces.ResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@ComponentScan({"edu.ksu.canvas"})
@Profile("dev")
public class CommonTestConfig {
    public static final int API_VERSION = 1;
    @Bean
    public String canvasBaseUrl() {
        return "domain";
    }

    @Bean(name = "restClient")
    public FakeRestClient restClient() {
        return new FakeRestClient();
    }

    @Bean(name = "canvasMessenger")
    public CanvasMessenger canvasMessenger() {
        return new RestCanvasMessenger(FakeRestClient.NO_TIMEOUT, FakeRestClient.NO_TIMEOUT, restClient());
    }

    @Bean(name = "canvasResponseParser")
    public ResponseParser canvasResponseParser() {
        return new GsonResponseParser();
    }
}
