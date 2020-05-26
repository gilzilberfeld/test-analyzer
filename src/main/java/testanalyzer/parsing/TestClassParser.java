package testanalyzer.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import testanalyzer.model.TestClassInfo;
import testanalyzer.parsing.exceptions.NoTestsFound;
import testanalyzer.parsing.rules.TestRules;
import testanalyzer.parsing.rules.TestClassRules;

public class TestClassParser {

	private CompilationUnit cu;
	
	public TestClassParser(String path) throws FileNotFoundException {
		this.cu = StaticJavaParser.parse(new File(path));
	}

	
	public boolean isTestClass() {
        TestClassRules testChecker = new TestClassRules();
        cu.accept(testChecker, null);
		return testChecker.hasTestMethods;
	}

	public int getNumberOfTests() throws Exception {
		if (isTestClass())
			return getTestQualityData().numberOfValidTests;
		return 0;
	}


	public TestClassInfo getTestQualityData() throws Exception {
		TestRules visitor = new TestRules();
		cu.accept(visitor, null);
		TestClassInfo testClassInfo = visitor.testClassInfo;
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
