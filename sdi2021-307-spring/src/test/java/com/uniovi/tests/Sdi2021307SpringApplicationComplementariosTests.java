package com.uniovi.tests;



import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils; 

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@SpringBootTest
public class Sdi2021307SpringApplicationComplementariosTests {
	
	//En Windows (Debe ser la versión 65.0.1 y desactivar las actualizaciones automáticas)):
	static String PathFirefox65= "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024= "C:\\Users\\Usuario\\Documents\\SDI\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	//En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas):
	//static String PathFirefox65= "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	//static String Geckdriver024= "/Users/delacal/selenium/geckodriver024mac";
	//Común a Windows y a MACOSX
	static WebDriver driver= getDriver(PathFirefox65, Geckdriver024); 
	static String URL= "http://localhost:8090";
	
	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
		
	}
	
	//Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}
	
	//Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}
	
	//Antes de la primera prueba
	@BeforeClass
	static public void begin() {
		
	}
	
	//Al finalizar la última prueba 
	@AfterClass 
	static public void end() {
		//Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}
	
	// PR16. Logearse como admin, vamos a registrar profesor con datos válidos, comprobamos que se ha añadido
	// PR16. Registro de profesores con datos válidos
	@Test
	public void PR16() {
		PO_PrivateView.loginAsAdmin(driver);
		PO_HomeView.clickOption(driver, "Gestión de Usuarios", "text", "btn btn-primary");
		PO_HomeView.clickOption(driver, "Agregar usuario", "text", "btn btn-primary");
		PO_PrivateView.fillForm(driver, "99999993E", "Manuel", "Ferreras", "123456");
		PO_View.checkElement(driver, "text", "99999993E");
		
	}
	
	// PR17. Logearse como admin, vamos a intentar registrar un profesor con datos inválidos y comprobamos que salen los mensajes
	// PR17. Regustro de profesores con datos inválidos
	@Test
	public void PR17() {
		PO_PrivateView.loginAsAdmin(driver);
		PO_HomeView.clickOption(driver, "Gestión de Usuarios", "text", "btn btn-primary");
		PO_HomeView.clickOption(driver, "Agregar usuario", "text", "btn btn-primary");
		PO_PrivateView.fillForm(driver, "", "Manuel", "Ferreras", "123456");
		PO_PrivateView.checkKey(driver, "Error.register.empty", PO_Properties.getSPANISH());
		PO_PrivateView.fillForm(driver, "99999993E", "", "Ferreras", "123456");
		PO_PrivateView.checkKey(driver, "Error.register.empty", PO_Properties.getSPANISH());
		PO_PrivateView.fillForm(driver, "99999993E", "Manuel", "", "123456");
		PO_PrivateView.checkKey(driver, "Error.register.empty", PO_Properties.getSPANISH());
	}
	
	// PR18. Logearse como profesor y como alumno y comprobar que no puedan dar de alta a un profesor
	// PR18. Ver que solo usuarios autorizados pueden dar de alta a un profesor
	@Test
	public void PR18() {
		PO_PrivateView.loginAsAdmin(driver);
		PO_HomeView.clickOption(driver, "Gestión de Usuarios", "text", "btn btn-primary");
		PO_PrivateView.loginAsStudent(driver);
		PO_HomeView.clickOption(driver, "Gestión de Usuarios", "text", "btn btn-primary");
	}
}
