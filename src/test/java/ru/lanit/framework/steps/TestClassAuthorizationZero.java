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

public class TestClassAuthorizationZero {
    private WebDriver webDriver;

    @BeforeTest
    public void setUp(){
        webDriver = WebDriverManager.getDriver();
        System.out.println("Запуск теста");
    }

    private void checkFields(String id){
        WebElement field = webDriver.findElement(By.id(id));
        WebElement fieldParrent = field.findElement(By.xpath(".."));
        String fielsError = fieldParrent.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(fielsError, "Это поле обязательно.");
    }

    @Test
    public void testLanitEducation() throws InterruptedException {
        webDriver.get("https://dev.n7lanit.ru/");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Lanit education");
        webDriver.findElement(By.xpath("//*[@class=\"btn navbar-btn btn-primary btn-register\"]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@class=\"btn btn-primary\"]")).click();
        Thread.sleep(5000);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class=\"help-block errors\"]")));
        checkFields("id_username");
        checkFields("id_email");
        checkFields("id_password");
    }

    @AfterTest
    public void close(){
        System.out.println("Тест завершен");
        webDriver.quit();
    }
}


