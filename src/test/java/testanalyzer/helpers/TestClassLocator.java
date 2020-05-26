package testanalyzer.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import testanalyzer.Tests;
import testanalyzer.parsing.TestClassResolver;

public class TestClassLocator {

	private static final String ROOT_PATH = "src/main/java/testanalyzer/examples/";
	private static final int DEPTH = 5;
	
	
	public static Tests loadTestClass(String className) throws FileNotFoundException {
		String filePath = findFilePath(className);
		TestClassResolver resolver = new TestClassResolver(filePath);
		return new Tests(resolver);
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
