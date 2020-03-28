package ru.lanit.framework.steps;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanit.framework.webdriver.WebDriverManager;

import java.util.Random;

public class TestClassAuthorization {
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
        fillFields("id_username","kuvaldinvitaly");
        Random random = new Random();
        int n = random.nextInt(100) + 1;
        String email = "kuvaldinvitaly" + n + "@gmail.com";
        fillFields("id_email",email);
        fillFields("id_password", n + "veryhardpassword");
        webDriver.findElement(By.xpath("//*[@class=\"btn btn-primary\"]")).click();
        Thread.sleep(5000);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"help-block errors\"]")));
        checkFields("id_username");


    }

    private void checkFields(String id){
        WebElement field = webDriver.findElement(By.id(id));
        WebElement fieldParrent = field.findElement(By.xpath(".."));
        String fielsError = fieldParrent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(fielsError, "Данное имя пользователя недоступно.");
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


