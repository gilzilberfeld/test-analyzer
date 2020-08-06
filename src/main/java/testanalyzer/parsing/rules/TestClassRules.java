package testanalyzer.parsing.rules;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import testanalyzer.model.TestClassType;

public class TestClassRules {

	public static boolean isSpringRunnerClass(TestClassType testClassType) {
		return (testClassType == TestClassType.Spring);
	}

	public static boolean isSpringBootTestClass(TestClassType testClassType) {
		return (testClassType == TestClassType.SpringBoot);
	}

	public static boolean hasSpringBootTestAnnotation(ClassOrInterfaceDeclaration cls) {
		return cls.getAnnotationByName("SpringBootTest").isPresent();
	}

	public static boolean hasRunWithAnnotation(ClassOrInterfaceDeclaration cls) {
		return cls.getAnnotationByName("RunWith").isPresent();
	}

	
	public static boolean runnerNameIs(ClassOrInterfaceDeclaration cls, String runnerName) {
		return getRunnerName(cls).contains(runnerName);
	}

	private static String getRunnerName(ClassOrInterfaceDeclaration cls) {
		return cls.getAnnotationByName("RunWith").get().getChildNodes().get(1).toString();
	}

}
