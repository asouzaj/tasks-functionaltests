package br.ce.wcaquino.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		//carregar a URL
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;


	}

	@Test
	public void deveSalvarComSucesso() {

		WebDriver driver = acessarAplicacao();

		try {





			//clickar botao Add todo
			driver.findElement(By.id("addTodo")).click();

			//entrar descricao
			driver.findElement(By.xpath("//*[@id=\"task\"]")).sendKeys("TAREFA MAIUSCULA 123");

			//entrar data
			driver.findElement(By.xpath("//*[@id=\"dueDate\"]")).sendKeys("24/09/2021");;

			//clicar botao salvar
			driver.findElement(By.id("saveButton")).click();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//validar a inclusao message Success!!!
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Success!!!", message);
		} finally {

			//fechar o browse
			driver.quit();
		}	


	}


	@Test public void naoDeveSalvarTarefaSemDescricao() {

		WebDriver driver = acessarAplicacao();

		try {





			//clickar botao Add todo 
			driver.findElement(By.id("addTodo")).click();

			//sem descricao 
			//driver.findElement(By.xpath("//*[@id=\"task\"]")).
			//sendKeys("TAREFA MAIUSCULA 3");

			//entrar data
			driver.findElement(By.xpath("//*[@id=\"dueDate\"]")).sendKeys("24/09/2021");;

			//clicar botao salvar 
			driver.findElement(By.id("saveButton")).click();



			//validar a message Fill the task description 
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Fill the task description", message); } finally {

				//fechar o browse 
				driver.quit(); 
			}


	}

	@Test public void naoDeveSalvarTarefaComDataPassada() {

		WebDriver driver = acessarAplicacao();

		try {





			//clickar botao Add todo 
			driver.findElement(By.id("addTodo")).click();

			//sem descricao 
			driver.findElement(By.xpath("//*[@id=\"task\"]")).
			sendKeys("TAREFA MAIUSCULA 3");

			//entrar data
			driver.findElement(By.xpath("//*[@id=\"dueDate\"]")).sendKeys("21/09/2021");;

			//clicar botao salvar 
			driver.findElement(By.id("saveButton")).click();



			//validar a message Fill the task description 
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Due date must not be in past", message); } finally {

				//fechar o browse 
				driver.quit(); 
			}


	}

	@Test
	public void naoSalvarComDataEmBranco() {

		WebDriver driver = acessarAplicacao();

		try {





			//clickar botao Add todo
			driver.findElement(By.id("addTodo")).click();

			//entrar descricao
			driver.findElement(By.xpath("//*[@id=\"task\"]")).sendKeys("TAREFA MAIUSCULA 111123");

			//entrar data
			//driver.findElement(By.xpath("//*[@id=\"dueDate\"]")).sendKeys("24/09/2021");;

			//clicar botao salvar
			driver.findElement(By.id("saveButton")).click();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//validar a inclusao message Success!!!
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Fill the due date", message);
		} finally {

			//fechar o browse
			driver.quit();
		}	


	}
}

