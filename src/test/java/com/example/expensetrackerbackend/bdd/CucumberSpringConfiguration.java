package com.example.expensetrackerbackend.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
    /**
     * This class is essential for gluing Cucumber with the Spring context.
     * The @SpringBootTest annotation loads the complete application context for testing.
     * RANDOM_PORT ensures that the application starts on a random available port,
     * preventing conflicts during test runs.
     */
}