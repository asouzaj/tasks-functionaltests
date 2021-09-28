package br.ce.wcaquino.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		// para usar driver local 
		// WebDriver driver = new ChromeDriver();
		
		//para usar driver do selenium grid/remoto
		Capabilities cap = DesiredCapabilities.chrome(); 
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.6:4444/wd/hub"), cap);
		
			
		
		//carregar a URL para testar funcional
		//carregar o ipconfig e não localhost
		driver.navigate().to("http://192.168.0.6:8001/tasks");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		return driver;


	}

	@Test
	public void deveSalvarComSucesso() throws MalformedURLException {

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

			

			//validar a inclusao message Success!!!
			String message = driver.findElement(By.id("message")).getText();

			Assert.assertEquals("Success!!!", message);
		} finally {

			//fechar o browse
			driver.quit();
		}	


	}


	@Test public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {

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

	@Test public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {

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
	public void naoSalvarComDataEmBranco() throws MalformedURLException {

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

