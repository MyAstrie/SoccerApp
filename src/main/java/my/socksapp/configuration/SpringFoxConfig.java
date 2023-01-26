package my.socksapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("my.socksapp.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("REST Приложение для склада с носками")
                .description("Данно веб-приложение позволяет учитывать и автоматизировать учет товаров на складе интернет-магазина носков. ")
                .version("1.0.0")
                .license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")
                .contact(new Contact("Andrey Zinchenko", null, "astrokoit@gmail.com"))
                .build();
    }
}