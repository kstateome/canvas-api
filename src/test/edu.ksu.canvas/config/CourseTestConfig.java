package config;

import edu.ksu.canvas.repository.OauthTokenRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by prakashreddy on 10/7/15.
 */
@Configuration
@Profile("dev")
@ComponentScan({"edu.ksu.canvas"})
public class CourseTestConfig {

    @Bean
    public OauthTokenRepository oauthTokenRepository(){
        return Mockito.mock(OauthTokenRepository.class);
    }
}
