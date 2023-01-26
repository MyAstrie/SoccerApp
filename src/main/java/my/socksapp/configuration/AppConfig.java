package my.socksapp.configuration;

import my.socksapp.repository.SocksRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SocksRepository socksRepository() {
        return new SocksRepository();
    }
}
