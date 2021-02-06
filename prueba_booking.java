package prueba_selenium;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;

public class prueba_booking {
    public WebDriver driver;
    String URL ="https://www.booking.com";
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver" , "driver/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 40);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterTest
    public void afterMethod(){
        driver.close();
    }


    @Test
    public void validarTituloTest(){
        String title = driver.getTitle();
        Assert.assertEquals(title, "Booking.com | Web oficial | Los mejores hoteles y alojamientos");
        Assert.assertNotEquals(driver.getCurrentUrl(),"https://www.booking.com/");
    }

    @Test
    public void mortarLinksTest(){
        List<WebElement> linkElements = driver.findElements(By.tagName("a"));
        for (WebElement li : linkElements){
            System.out.println("Elementos li: " + li.getText());
        }
    }

    @Test
    public void mortarH1sTest(){
        List<WebElement> listH1 = driver.findElements(By.tagName("H1"));
        System.out.println("La pagina contiene   " + listH1.size() + " H1");

        for (WebElement h1: listH1){
            System.out.println("h1: " + h1.getText());
        }
    }

    @Test
    public void buscarGenteViajeraTest(){
        List<WebElement> listH2 = driver.findElements(By.tagName("H2"));
        System.out.println("La pagina contiene   " + listH2.size() + " H2");

        String h2Text="";

        for (WebElement h2: listH2){
            if(h2.getText().equals("Conecta con gente viajera")){
                h2Text = h2.getText();
            }
        }

        Assert.assertEquals(h2Text, "Conecta con gente viajera");

        //Corregir este

    }

    @Test
    public void  registroUsuarioTests(){
        Faker faker_data = new Faker();
       String email = faker_data.internet().emailAddress();
       String contrasena = faker_data.internet().password();
       driver.findElement(By.xpath("//span[text()[normalize-space()='Inicia sesión']]")).click();
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.xpath("//span[text()='Continuar con e-mail']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_password")));
        driver.findElement(By.id("new_password")).sendKeys(contrasena);
        driver.findElement(By.id("confirmed_password")).sendKeys(contrasena.concat("123"));
        driver.findElement(By.xpath("(//span[@class='bui-button__text'])[2]")).click();
        try {
            Assert.assertTrue(driver.findElement(By.id("confirmed_password-description")).isDisplayed());
        }catch (NoSuchElementException e){
            System.out.println("No se muestra el mensaje");
        }
    }


    @DataProvider(name = "datosEmails")
    public Object [][] emails(){
        return new Object[][]{
                {"lelele@test.com," },
                {"jejejje@test.coml"},
                {"teteete@test.comj"},
        };
    }
    private final String REGISTRED_EMAIL_ERROS_MSG = "Comprueba si el e-mail que has introducido es correcto";
    @Test (dataProvider = "datosEmails")
    public void testData(String emails){
        Assert.assertNotEquals(driver.getCurrentUrl(),"https://www.booking.com/");
        driver.findElement(By.xpath("//span[text()[normalize-space()='Inicia sesión']]")).click();
        driver.findElement(By.id("username")).sendKeys(emails);


       WebDriverWait wait = new WebDriverWait(driver, 30);
       wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Comprueba si el e-mail que has introducido es correcto')]"))));

       WebElement erroMsg =driver.findElement(By.xpath("//*[contains(text(), 'Comprueba si el e-mail que has introducido es correcto')]"));

       Assert.assertNotEquals(erroMsg.getText(), REGISTRED_EMAIL_ERROS_MSG);


    }



}