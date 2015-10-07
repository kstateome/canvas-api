package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by prakashreddy on 10/7/15.
 */
@Configuration
@Profile("dev")
@ComponentScan({"edu.ksu.canvas"})
public class BaseTestConfig {
}
