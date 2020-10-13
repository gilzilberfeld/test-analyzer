package testanalyzer.export;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiFileSerializationTests {

	private static final String TESTS_PATH = "src/main/java/testanalyzer/examples/asserts";
	private Path outputDir;
	
	@BeforeEach
	public void setup() throws IOException {
		//outputDir = Files.createTempDirectory("any");
		outputDir = Paths.get("C:\\Users\\Gil\\AppData\\Local\\Temp\\any16463833377661701882");
	}

	@AfterEach
	public void tearDown() {
		
	}
	
	@Test
	public void export() throws Exception {
		MultiFileExporter.writeJson(TESTS_PATH, outputDir.toString());
		File dir = new File(outputDir.toString());
		assertTrue (runFileExists(dir));
		assertTrue (classFilesExist(dir));
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
}
