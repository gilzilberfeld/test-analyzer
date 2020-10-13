package testanalyzer.parsing;

import java.io.File;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestClassType;
import testanalyzer.parsing.asserts.AssertInfoHelper;
import testanalyzer.parsing.checkers.TestChecker;
import testanalyzer.parsing.checkers.TestClassTypeChecker;
import testanalyzer.parsing.rules.TestClassRules;

public class TestClassParser {

	private CompilationUnit cu;
	private String path;

	public TestClassParser(String path) throws Exception {
		this.path = path;
		this.cu = StaticJavaParser.parse(new File(path));
	}

	public int getNumberOfTests() throws Exception {
		if (TestClassRules.isTestClass(cu))
			return getTestClassInfo().numberOfValidTests;
		return 0;
	}

	public TestClassInfo getTestClassInfo() throws Exception {
		TestClassInfo testClassInfo = new TestClassInfo(getTestClassType(), getTestClassName());
		testClassInfo.classPath = this.path;
		AssertInfoHelper assertHelper = new AssertInfoHelper();
		TestChecker testChecker = new TestChecker(testClassInfo, assertHelper);
		cu.accept(testChecker, null);
		assertHelper.updateTestInfos(testClassInfo.testsInfo);
		return testClassInfo;
	}


	public String getTestClassName() throws Exception {
		Optional<String> primaryTypeName = cu.getPrimaryTypeName();
		if (primaryTypeName.isPresent() && TestClassRules.isTestClass(cu))
			return primaryTypeName.get();
		
		return TestClassInfo.NoName;
	}

	public  TestClassType getTestClassType() {
		TestClassTypeChecker testClassTypeChecker = new TestClassTypeChecker();
		cu.accept(testClassTypeChecker, null);
		return testClassTypeChecker.type;
	}

}
