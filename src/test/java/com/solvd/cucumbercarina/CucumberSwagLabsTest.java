package com.solvd.cucumbercarina;

import com.qaprosoft.carina.core.foundation.cucumber.CucumberBaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/SwagLabs.feature",
        glue = "com.solvd.cucumbercarina.cucumber.steps",
        plugin={"pretty",
                "html:target/cucumber-core-test-report",
                "pretty:target/cucumber-core-test-report.txt",
                "json:target/cucumber-core-test-report.json",
                "junit:target/cucumber-core-test-report.xml"}
)
public class CucumberSwagLabsTest extends CucumberBaseTest {
    //do nothing here as everything is declared in "SwagLabs.feature" and steps

}
