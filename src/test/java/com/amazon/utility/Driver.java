package com.amazon.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private Driver(){} //Singleton

    private static InheritableThreadLocal<WebDriver> driverPool=new InheritableThreadLocal<>();
    public static WebDriver getDriver(){
        if(driverPool.get()==null){
            String browser= System.getProperty("browser") !=null ? System.getProperty("browser"):ConfigReader.getProperty("browser");

            switch (browser){
                case "chrome":
                    ChromeOptions options=new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    driverPool.set(new FirefoxDriver());
            }

        }
        return driverPool.get();
    }

    public static void closeDriver(){
        driverPool.get().quit();
        driverPool.remove();
    }
}
