package com.exadel.project.integration;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = CommonITContext.DockerMySQLDataSourceInitializer.class)
@Testcontainers
public abstract class CommonITContext {

    @Container
    public static MySQLContainer<?> mySQLDBContainer = new MySQLContainer<>("mysql:8.0.22")
            .withUsername("mysql")
            .withPassword("password")
            .withDatabaseName("exadel_internships");


    static {
        mySQLDBContainer.start();
    }

    public static class DockerMySQLDataSourceInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    context,
                    "spring.datasource.url=" + mySQLDBContainer.getJdbcUrl(),
                    "spring.datasource.username=" + mySQLDBContainer.getUsername(),
                    "spring.datasource.password=" + mySQLDBContainer.getPassword()
            );
        }
    }
}
