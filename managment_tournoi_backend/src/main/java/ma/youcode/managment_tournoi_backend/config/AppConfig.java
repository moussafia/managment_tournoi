package ma.youcode.managment_tournoi_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

@Configuration
public class AppConfig {
    @Bean
    public ResourceLoader resourceLoader(){
        return new DefaultResourceLoader();
    }
    @Bean
    @Primary
    public FreeMarkerConfigurationFactory freeMarkerConfigurationFactory(){
        FreeMarkerConfigurationFactory freeMarkerConfigurationFactory = new FreeMarkerConfigurationFactory();
        freeMarkerConfigurationFactory.setTemplateLoaderPath("classpath:/templates");
        return freeMarkerConfigurationFactory;
    }

}
