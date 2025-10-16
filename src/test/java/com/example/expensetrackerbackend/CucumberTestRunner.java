package com.example.expensetrackerbackend;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.expensetrackerbackend.bdd")
public class CucumberTestRunner {
    // This class acts as a bridge between JUnit and Cucumber.
    // It tells JUnit to use the Cucumber engine to run tests.
    // @SelectClasspathResource("features") points to our .feature files.
    // @ConfigurationParameter for GLUE_PROPERTY_NAME points to our step definitions package.
}