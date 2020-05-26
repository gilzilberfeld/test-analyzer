package testanalyzer.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import testanalyzer.model.TestClassQuality;
import testanalyzer.parsing.exceptions.NoTestsFound;

public class TestClassParser {

	private CompilationUnit cu;
	
	public TestClassParser(String path) throws FileNotFoundException {
		this.cu = StaticJavaParser.parse(new File(path));
	}

	
	public boolean isTestClass() {
        TestClassChecker testChecker = new TestClassChecker();
        cu.accept(testChecker, null);
		return testChecker.hasTestMethods;
	}

	public int getNumberOfTests() throws Exception {
		if (isTestClass())
			return getTestQualityData().numberOfValidTests;
		return 0;
	}


	public TestClassQuality getTestQualityData() throws Exception {
		QualityChecker visitor = new QualityChecker();
		cu.accept(visitor, null);
		TestClassQuality testClassInfo = visitor.testClassInfo;
		testClassInfo.testClassName = getTestClassName();
		return testClassInfo;
	}


	public String getTestClassName() throws Exception {
		Optional<String> primaryTypeName = cu.getPrimaryTypeName();
		if (primaryTypeName.isPresent() && isTestClass())
			return primaryTypeName.get();
		throw new NoTestsFound();
	}


}
