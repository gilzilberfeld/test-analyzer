package testanalyzer.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import testanalyzer.parsing.TestClassAdapter;
import testanalyzer.parsing.TestClassParser;

public class TestLocator {

	private static final String ROOT_PATH = "src/main/java/testanalyzer/examples/";
	private static final int DEPTH = 5;
	
	
	public static TestClassAdapter loadTestClass(String className) throws FileNotFoundException {
		String filePath = findFilePath(className);
		TestClassParser resolver = new TestClassParser(filePath);
		return new TestClassAdapter(resolver);
	}

	private static String findFilePath(String className) {
		Path rootPath = Paths.get(ROOT_PATH);
		
		String fileName = className + ".java";
		return getPath(rootPath, fileName);
	}

	private static String getPath(Path rootPath, String fileName) {
		String filePath="";
		try (Stream<Path> stream = Files.find(rootPath, DEPTH,
				(path, attr) -> path.getFileName().toString().equals(fileName))) {
			Optional<Path> result = stream.findFirst();
			if (result.isPresent()) {
				filePath = result.get().toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
}
