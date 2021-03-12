package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep){
		//Esperamos 5 segundo aque carge el DOM porque en algunos equipos falla 
		SeleniumUtils.esperarSegundos(driver, 5);
		//Seleccionamos el alumnos user Order
		new Select (driver.findElement(By.id("user"))).selectByIndex(userOrder);
		//Rellenemos el campo de descripción 
		WebElement description= driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score= driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton= By.className("btn");
		driver.findElement(boton).click();
	}
	
	static private void login(WebDriver driver, String username, String password) {
		//Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		//Rellenamos el formulario
		PO_LoginView.fillForm(driver, username, password);
	}
	
	static public void loginAsStudent(WebDriver driver) {
		login(driver,"99999990A", "123456");
		//COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}
	
	static public void loginAsProfessor(WebDriver driver) {
		login(driver, "99999993D", "123456");
		//COmprobamos queentramosenlapaginaprivadadelProfesor
		PO_View.checkElement(driver, "text", "99999993D");
	}
	
	static public void loginAsAdmin(WebDriver driver) {
		login(driver, "99999988F", "123456");
		
	}
}
