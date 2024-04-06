package ma.youcode.managment_tournoi_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class AppConfig {
    @Bean
    public ResourceLoader resourceLoader(){
        return new DefaultResourceLoader();
    }
}
