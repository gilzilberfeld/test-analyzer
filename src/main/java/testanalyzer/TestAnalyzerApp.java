package testanalyzer;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class TestAnalyzerApp {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Test Analyzer:");
			System.out.println("Provide path to test folder and output file.");
			return;
		}

		String pathToFolder = args[0];
		String outputFileName = args[1];

		try {
			TestContainer testContainer = TestContainer.LoadFrom(pathToFolder);
			String json = testContainer.toJson();
			FileWriter outputFile = new FileWriter(outputFileName);
			outputFile.write(json);
			outputFile.close();
		} catch (Exception e) {
			System.out.println("Test Analyzer error:");
			e.printStackTrace();
		}
	}
}
