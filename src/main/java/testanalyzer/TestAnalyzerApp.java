package testanalyzer;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class TestAnalyzerApp {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Test Analyzer arguments:");
			System.out.println("1) path to test folder");
			System.out.println("2) output file name");
			return;
		}

		String pathToFolder = args[0];
		String outputFileName = args[1];

		writeJson(pathToFolder, outputFileName);
	}

	private static void writeJson(String pathToFolder, String outputFileName) {
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
