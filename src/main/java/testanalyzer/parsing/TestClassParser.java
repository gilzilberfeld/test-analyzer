package testanalyzer.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import testanalyzer.model.TestClassInfo;
import testanalyzer.parsing.rules.TestClassRules;
import testanalyzer.parsing.rules.TestClassTypeRules;
import testanalyzer.parsing.rules.TestRules;

public class TestClassParser {

	private CompilationUnit cu;

	public TestClassParser(String path) throws FileNotFoundException {
		this.cu = StaticJavaParser.parse(new File(path));
	}

	public boolean isTestClass() {
		TestClassRules testClassChecker = new TestClassRules();
		cu.accept(testClassChecker, null);
		return testClassChecker.hasTestMethods;
	}

	public int getNumberOfTests() throws Exception {
		if (isTestClass())
			return getTestClassInfo().numberOfValidTests;
		return 0;
	}

	public TestClassInfo getTestClassInfo() throws Exception {
		TestRules visitor = new TestRules();
		cu.accept(visitor, null);
		TestClassInfo testClassInfo = visitor.getTestClassInfo();
		testClassInfo.type = getTestClassType();
		testClassInfo.testClassName = getTestClassName();
		return testClassInfo;
	}


	public String getTestClassName() throws Exception {
		Optional<String> primaryTypeName = cu.getPrimaryTypeName();
		if (primaryTypeName.isPresent() && isTestClass())
			return primaryTypeName.get();
		return TestClassInfo.NoName;
	}

	public static boolean isSpringBootTestClass(MethodDeclaration method) {
		ClassOrInterfaceDeclaration classNode = (ClassOrInterfaceDeclaration) method.getParentNode().orElse(null);
		return classNode.getAnnotationByName("SpringBootTest").isPresent();
	}

	public  TestClassType getTestClassType() {
		TestClassTypeRules testClassTypeChecker = new TestClassTypeRules();
		cu.accept(testClassTypeChecker, null);
		return testClassTypeChecker.type;
	}

}
