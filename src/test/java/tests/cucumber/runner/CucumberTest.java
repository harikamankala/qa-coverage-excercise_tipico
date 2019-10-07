package tests.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/cucumber/",
        plugin = {"pretty",
	    	      "html:target/site/cucumber-pretty",
	    	      "json:target/cucumber.json" },
        glue = "tests.cucumber.glue"
)
public class CucumberTest {
}
