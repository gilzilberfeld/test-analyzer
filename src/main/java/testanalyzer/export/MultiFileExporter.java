package testanalyzer.export;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

	private static void writeClassContentFiles(TestContainer testContainer) throws Exception {
			for (int i=0; i< testContainer.getTestClassCount(); i++) {
				ClassFileInfo nextClassFile = fileManager.getNextClassFile();
				Path file = outputPath.resolve(nextClassFile.name);
				Files.write(file, nextClassFile.content.getBytes());
				List<TestFileInfo> nextTestFiles = fileManager.getNextTestFiles();
				nextTestFiles.forEach(testFile -> {
					Path testfile = outputPath.resolve(testFile.name);
					try {
						Files.write(testfile, testFile.content.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			}
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