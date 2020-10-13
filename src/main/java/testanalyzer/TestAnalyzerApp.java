package testanalyzer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import testanalyzer.export.SingleFileExporter;

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

		SingleFileExporter.writeJson(pathToFolder, outputFileName);
	}
}
