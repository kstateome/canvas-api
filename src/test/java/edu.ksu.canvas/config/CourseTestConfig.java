package edu.ksu.canvas.config;

import edu.ksu.canvas.repository.OauthTokenRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
@ComponentScan({"edu.ksu.canvas"})
public class CourseTestConfig {

    @Bean
    public OauthTokenRepository oauthTokenRepository(){
        return Mockito.mock(OauthTokenRepository.class);
    }
}
