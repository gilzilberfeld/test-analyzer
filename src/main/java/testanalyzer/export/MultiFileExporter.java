package testanalyzer.export;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import testanalyzer.TestContainer;

public class MultiFileExporter {
	static MultiFileManager fileManager;
	static Path outputPath;

	public static void writeJson(String pathToFolder, String outputFolder) throws Exception {
		outputPath = Path.of(outputFolder);

		TestContainer testContainer = TestContainer.LoadFrom(pathToFolder);
		fileManager = new MultiFileManager(testContainer);

		CreateFolderIfNeeded();
		writeRunContentFile();
		writeClassContentFiles(testContainer);
//	
//	getAllTestClassInfo.forEach(()-> {
//		fileName = GetNextClassName();
//		write ( fileName, classContent);
//	});
//	
//	getAllTestInfo.forEach(()-> {
//		fileName = GetNextClassName();
//		write ( fileName, classContent);
//	}
//	
//	
//	
//	
//	
//	try (FileWriter outputFile = new FileWriter(outputFolderName)){
//		String json = testContainer.toJson();
//		outputFile.write(json);
//	} catch (Exception e) {
//		System.out.println("Test Analyzer error:");
//		e.printStackTrace();
//	}
//}
//

	}

	private static void writeClassContentFiles(TestContainer testContainer) {
		// TODO Auto-generated method stub
//		testContainer.testClasses.forEach((testClass) -> {
//			Path file = outputPath.resolve
//		}
	}

	private static void writeRunContentFile() throws Exception {
		Path file = outputPath.resolve(fileManager.getRunInfoFile().name);
		Files.write(file, fileManager.getRunInfoFile().content.getBytes());
	}
	

	private static void CreateFolderIfNeeded() throws IOException {
		if (Files.notExists(outputPath)) {
			Files.createDirectory(outputPath);
		}
	}
}