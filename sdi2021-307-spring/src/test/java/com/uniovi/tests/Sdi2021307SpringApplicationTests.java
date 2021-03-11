package com.uniovi.tests;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View; 

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@SpringBootTest
public class Sdi2021307SpringApplicationTests {
	
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
	
	//PR01. Acceder a la página principal /
	@Test
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}
	
	//PR02. OPción de navegación. Pinchar en el enlace Registro en la página home
	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}
	//PR03. OPción de navegación. Pinchar en el enlace Identificate en la página home 
	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login",  "class","btn btn-primary");
	}
	
	//PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta a Español
	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		//SeleniumUtils.esperarSegundos(driver, 2);
	}
	
	//PR05. Pruebadelformularioderegistro. registro con datos correctos
	@Test
	public void PR05() {
		//Vamosalformularioderegistro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		//Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
		//Comprobamosqueentramosenlasecciónprivada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	//PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombre corto, .... pagination pagination-centered, Error.signup.dni.length
	@Test
	public void PR06() {
		//Vamos al formulario de registro 
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		//COmprobamos el error de DNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH() );
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez","77777", "77777");
		//COmprobamos el error de Nombrecorto.
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH() );
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "88888880A", "Josefo", "Per", "77777", "77777");
		//COmprobamos el error de Apellido corto.
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH() );
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "77777770B", "Josefo", "Perez", "777", "77777");
		//COmprobamos el error de contraseña corta.
		PO_RegisterView.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH() );
		//Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "66666660B", "Josefo", "Per", "77777", "88888");
		//COmprobamos el error de contraseña coincide.
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH() );				
	}
	
	@Test
	public void PR07() {
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		//COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}
	
	@Test
	public void PR08() {
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456");
		//COmprobamos que entramos en la pagina privada de profesores
		PO_View.checkElement(driver, "text", "Lista de alumnos");
	}
	
	@Test
	public void PR09() {
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999988F", "123456");
		//COmprobamos que entramos en la pagina privada de admin
		PO_View.checkElement(driver, "text", "Lista de profesores");
	}
	
	@Test
	public void PR10() {
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, "999990A", "123456");
		//COmprobamos que no entramos
		PO_View.checkElement(driver, "text", "Identifícate");
	}
	
	@Test
	public void PR11() {
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		//COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
		//Salimos
		PO_LoginView.pressLogout(driver);
		//Comprobamos que salimos 
		PO_View.checkElement(driver, "text", "Identifícate");
	}
}
