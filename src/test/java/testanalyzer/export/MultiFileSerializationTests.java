package testanalyzer.export;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiFileSerializationTests {

	private static final String TESTS_PATH = "src/main/java/testanalyzer/examples/asserts";
	private Path outputDir;
	
	@BeforeEach
	public void setup() throws IOException {
		outputDir = Files.createTempDirectory("any");
	}

	@AfterEach
	public void tearDown() throws IOException {
		 deleteOutputDir();
	}

	private void deleteOutputDir() throws IOException {
		Files.walk(outputDir)
	      .sorted(Comparator.reverseOrder())
	      .map(Path::toFile)
	      .forEach(File::delete);
	}
	
	@Test
	public void export() throws Exception {
		MultiFileExporter.writeJson(TESTS_PATH, outputDir.toString());
		File dir = new File(outputDir.toString());
		assertTrue (runFileExists(dir));
		assertTrue (classFilesExist(dir));
		assertTrue (testFilesExist(dir));
	}

	private boolean runFileExists(File dir) {
		return Arrays.stream(dir.listFiles())
				.anyMatch((file) ->{
					return file.getName().endsWith("_Run.json");
				});
	}

	private boolean classFilesExist(File dir) {
		return Arrays.stream(dir.listFiles())
			.anyMatch((file) ->{
				return file.getName().contains("_Class_");
		});
	}

	private boolean testFilesExist(File dir) {
		return Arrays.stream(dir.listFiles())
			.anyMatch((file) ->{
				return file.getName().contains("_Test_");
		});
	}
}
