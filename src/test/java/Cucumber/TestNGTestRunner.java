package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
//cucumber depend on testNg runner or junit runner

@CucumberOptions(features="src/test/java/Cucumber",glue="F10StepDefinitions",
monochrome=true, tags = "@Regression", plugin= {"html:target/Cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests 
{
	
	
}
