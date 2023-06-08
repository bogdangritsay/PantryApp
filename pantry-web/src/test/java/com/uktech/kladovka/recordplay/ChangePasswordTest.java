package com.uktech.kladovka.recordplay;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class ChangePasswordTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "/Users/mac/ITProjects/chromedriver_mac64/chromedriver");

    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void changepassword() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1208, 687));
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.name("email")).sendKeys("test1@gmail.com");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).sendKeys("12345");
    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
    driver.findElement(By.linkText("test1@gmail.com")).click();
    driver.findElement(By.linkText("Profile")).click();
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).sendKeys("54321");
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.linkText("test1@gmail.com")).click();
    driver.findElement(By.cssSelector(".d-flex > .dropdown-item")).click();
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).sendKeys("test1@gmail.com");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).sendKeys("12345");
    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
  }
}
