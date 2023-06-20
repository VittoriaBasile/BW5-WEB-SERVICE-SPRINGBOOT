package epicode.EPICENERGYSERVICE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProvinciaRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		String filePath = new File("province-italiane.csv").getAbsolutePath();
		boolean isFirstLine = true;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				String[] columns = line.split(";");
				String siglaProvincia = columns[0];
				String provincia = columns[1];
				String regione = columns[2];

				System.out.println("Sigla: " + siglaProvincia + ", provincia: " + provincia + ", Regione: " + regione);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
