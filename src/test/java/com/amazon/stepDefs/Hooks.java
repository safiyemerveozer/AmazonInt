package com.amazon.stepDefs;

import com.amazon.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

public class Hooks {
/*
   @Before
   public void setUp(){
       Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       Driver.getDriver().manage().window().maximize();
   }

 */


    @After
    public void tearDown(Scenario scenario){

        if (scenario.isFailed()){
            var screenshot= ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png",scenario.getName());

        }


        Driver.closeDriver();
    }
}
