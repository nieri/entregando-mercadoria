package br.com.munieri.entregando.mercadoria;

import br.com.munieri.entregando.mercadoria.boot.environment.ApplicationInitializer;
import com.google.gson.Gson;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

@SpringApplicationConfiguration(classes = ApplicationInitializer.class)
@WebAppConfiguration
@ActiveProfiles("test")
@Transactional
public class AppConfWebTest {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mvc;

    protected Gson gson = new Gson();

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

}
