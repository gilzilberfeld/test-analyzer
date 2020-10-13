package testanalyzer.export;

import java.io.FileWriter;

import testanalyzer.TestContainer;

public class SingleFileExporter {

	public static void writeJson(String pathToFolder, String outputFileName) {
		try (FileWriter outputFile = new FileWriter(outputFileName)){
			TestContainer testContainer = TestContainer.LoadFrom(pathToFolder);
			String json = testContainer.toJson();
			
			outputFile.write(json);
			
		} catch (Exception e) {
			System.out.println("Test Analyzer error:");
			e.printStackTrace();
		}
	}

}
