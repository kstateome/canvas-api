package edu.ksu.canvas.config;

import edu.ksu.canvas.net.FakeRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
@ComponentScan({"edu.ksu.canvas"})
public class BaseTestConfig {
    @Bean
    public FakeRestClient fakeRestClient() {
        return new FakeRestClient();
    }
}
