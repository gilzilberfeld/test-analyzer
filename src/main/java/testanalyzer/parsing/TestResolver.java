package testanalyzer.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import testanalyzer.TestClassQuality;
import testanalyzer.parsing.exceptions.NoClassFound;
import testanalyzer.parsing.exceptions.NoTestsFound;

public class TestResolver {

	private CompilationUnit cu;
	
	public TestResolver(String path) throws FileNotFoundException {
		this.cu = StaticJavaParser.parse(new File(path));
	}

	
	public boolean isTestClass() {
        TestClassChecker testChecker = new TestClassChecker();
        cu.accept(testChecker, null);
		return testChecker.hasTestMethods;
	}

	public int getNumberOfTests() {
		if (isTestClass())
			return getTestQualityData().numberOfValidTests;
		return 0;
	}


	public TestClassQuality getTestQualityData() {
		QualityChecker visitor = new QualityChecker();
		cu.accept(visitor, null);
		return visitor.testClassInfo;
	}


	public String getTestClassName() throws Exception {
		boolean isTestClass = isTestClass();

		Optional<String> primaryTypeName = cu.getPrimaryTypeName();
		if (primaryTypeName.isPresent() && isTestClass)
			return primaryTypeName.get();
		throw new NoTestsFound();
	}


}
