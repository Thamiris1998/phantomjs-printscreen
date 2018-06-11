package robo;

import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class PrintScreen {

	public static void main(String[] args) throws Exception {

		Integer num = 0;
		File dir = new File("C:\\PrintScreeen");
		dir.mkdir();
		File file;

		while(true) {

			//PhantomJSDriver server para acessar a página sem exibir o navegador roda em background
			System.setProperty("phantomjs.binary.path", "C:\\Users\\tlopes\\Downloads\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
			WebDriver driver = new PhantomJSDriver();

			//Fullscreen
			driver.manage().window().maximize();
			driver.get("http://www.oi.com.br/");
			
			//Faz o printScreen do site
			final Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(500)).takeScreenshot(driver);
			
			//Enquanto a condição for verdadeira executa o while
			while(true) {
			
				//Define o diretorio do file
				file = new File("C:\\printscreeen\\" + num + ".png");
				
				//Se o file for diferente do existente ele para(break) e incrementa o num do file
				if (!file.exists()) {
					break;
				}
				num++;
			}
			
			//Cria o file
			file.createNewFile();
			
			//Formata o file
			ImageIO.write((RenderedImage) screenshot.getImage(), "PNG",file);
			
			System.out.println("Salvo");
			//Thread.sleep(10000);
		}

	}
}
