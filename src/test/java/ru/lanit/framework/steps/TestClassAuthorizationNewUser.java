package ru.lanit.framework.steps;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanit.framework.webdriver.WebDriverManager;

import java.util.Random;

public class TestClassAuthorizationNewUser {
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
        webDriver.findElement(By.xpath("//*[@class=\"btn navbar-btn btn-primary btn-register\"]")).click();
        Thread.sleep(1000);
        Random random = new Random();
        int n = random.nextInt(100) + 1;
        fillFields("id_username","newNewTestUser" + n);
        String email = "newNewTestUser" + n + "@gmail.com";
        fillFields("id_email",email);
        fillFields("id_password", n + "veryhardpassword");
        webDriver.findElement(By.xpath("//*[@class=\"btn btn-primary\"]")).click();
        Thread.sleep(5000);

    }

    private void fillFields(String id, String text){
        WebElement field = webDriver.findElement(By.id(id));
        field.sendKeys(text);
    }

    @AfterTest
    public void close(){
        System.out.println("Тест завершен");
        webDriver.quit();
    }
}


