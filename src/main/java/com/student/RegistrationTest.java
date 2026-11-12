
package com.student;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;

public class RegistrationTest {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {

            String path = Paths.get(
                "src/main/resources/registration.html"
            ).toAbsolutePath().toUri().toString();

            driver.get(path);

            // TEST CASE 1: Successful Registration

            driver.findElement(By.id("studentName"))
                  .sendKeys("Sinchana");

            driver.findElement(By.id("email"))
                  .sendKeys("sinchana@gmail.com");

            driver.findElement(By.id("usn"))
                  .sendKeys("4VV24AI001");

            Select dept = new Select(
                driver.findElement(By.id("department"))
            );

            dept.selectByVisibleText("AIML");

            driver.findElement(By.id("submit")).click();

            String result = driver.findElement(
                By.id("message")
            ).getText();

            if(result.equals("Registration successful!")) {

                System.out.println(
                    "Test 1 Passed: Registration Successful"
                );

            } else {

                throw new AssertionError("Test 1 Failed");

            }

            // TEST CASE 2: Empty Field Validation

            driver.navigate().refresh();

            driver.findElement(By.id("submit")).click();

            String validation = driver.findElement(
                By.id("message")
            ).getText();

            if(validation.equals("Please fill in all fields.")) {

                System.out.println(
                    "Test 2 Passed: Empty Field Validation"
                );

            } else {

                throw new AssertionError("Test 2 Failed");

            }

            System.out.println("All Selenium Tests Passed!");

        }

        finally {

            driver.quit();

        }

    }
}
