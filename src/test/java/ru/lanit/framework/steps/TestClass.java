package ru.lanit.framework.steps;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanit.framework.webdriver.WebDriverManager;


public class TestClass {
    private WebDriver webDriver;

    @BeforeTest
    public void setUp(){
        webDriver = WebDriverManager.getDriver();
        System.out.println("Запуск теста");
    }

    @Test
    public void testLanitEducation() throws InterruptedException {
        webDriver.get("https://dev.n7lanit.ru/");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Lanit education");
        webDriver.findElement(By.xpath("//*[@href=\"/categories/\"]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@href=\"/users/\"]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@href=\"/search/\"]")).click();
        WebElement webElement = webDriver.findElement(By.xpath("//*[@class=\"form-control\"]"));
        Thread.sleep(1000);
        webElement.sendKeys("kuvaldinvitaly");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@class=\"dropdown-search-footer\"]")).click();
        Thread.sleep(1000);
    }

    @AfterTest
    public void close(){
        System.out.println("Тест завершен");
        webDriver.quit();
    }
}


