package Practico11;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Practico11 {
    @Test
    public void completeDocusignRegistrationForm(){
        System.setProperty("webdriver.chrome.driver" , "driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://go.docusign.com/o/trial/");

        driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("Opi");
        driver.findElement(By.xpath("(//input[@class='form-control'])[2]")).sendKeys("Curotto");
        driver.findElement(By.xpath("(//input[@class='form-control'])[3]")).sendKeys("Opi@test.com");
        driver.findElement(By.xpath("//input[@data-extended-regex='/^([0-9\\(\\)\\/\\+ \\-]*)$/gi']")).sendKeys("333");
        driver.findElement(By.xpath("//input[@data-extended-regex='/[^]*/gi']")).sendKeys("superTrabajo");

    }

    ///////Ejercicio3////

    @Test
    public void spotifyByNameTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver" , "driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.spotify.com/uy/signup/");

        driver.findElement(By.name("email")).sendKeys("opi@test.com");
        driver.findElement(By.name("confirm")).sendKeys("opi@test.com");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("displayname")).sendKeys("Opiiiis");



    }

    //Ejercicio 4/////
    @Test
    public void spotifyByPlaceHolder(){
        System.setProperty("webdriver.chrome.driver" , "driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.spotify.com/uy/signup/");

        driver.findElement(By.xpath("//input[@placeholder='Introduce tu correo electrónico.']")).sendKeys("rrr@test.com");
        driver.findElement(By.xpath("//input[@placeholder='Vuelve a introducir tu correo electrónico.']")).sendKeys("rrr@test.com");
        driver.findElement(By.xpath("//input[@placeholder='Crea una contraseña.']")).sendKeys("123");
        driver.findElement(By.xpath("//input[@placeholder='Introduce un nombre de perfil.']")).sendKeys("Shiliii");
    }
}
