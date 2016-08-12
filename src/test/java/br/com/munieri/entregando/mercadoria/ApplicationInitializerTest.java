package br.com.munieri.entregando.mercadoria;

import br.com.munieri.entregando.mercadoria.boot.environment.ApplicationInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("test")
@PropertySource("classpath:application-test.properties")
public class ApplicationInitializerTest extends ApplicationInitializer{

    public ApplicationInitializerTest() throws Exception {
    }
}
