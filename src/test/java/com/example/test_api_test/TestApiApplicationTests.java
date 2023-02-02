package com.example.test_api_test;

import com.example.test_api.app.TestApiApplication;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBootConfiguration
class TestApiApplicationTests {


    private void assertInsertSucceeds(ConfigurableApplicationContext context) {
        String name = "A";

        MongoTemplate mongo = context.getBean(MongoTemplate.class);
        Document doc = Document.parse("{\"name\":\"" + name + "\"}");
        Document inserted = mongo.insert(doc, "items");

        assertNotNull(inserted.get("_id"));
        assertEquals(inserted.get("name"), name);
    }

    @Test
    public void whenPropertiesConfig_thenInsertSucceeds() {
        SpringApplicationBuilder app = new SpringApplicationBuilder(TestApiApplication.class);
        app.run();

        assertInsertSucceeds(app.context());
    }

    /*@Test
    public void whenClientConfig_thenInsertSucceeds() {
        SpringApplicationBuilder app = new SpringApplicationBuilder(TestApiApplication.class);
        app.web(WebApplicationType.NONE)
                .run();

        assertInsertSucceeds(app.context());
    }*/

}