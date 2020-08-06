package testanalyzer.parsing.rules;

import com.github.javaparser.ast.body.MethodDeclaration;

import testanalyzer.model.TestClassType;
import testanalyzer.model.TestType;
import testanalyzer.parsing.checkers.APIChecker;
import testanalyzer.parsing.checkers.ExpectedJUnit4Checker;
import testanalyzer.parsing.checkers.ExpectedRuleJUnit4Checker;

public class TestRules {

	public static boolean callsAPI(MethodDeclaration method) {
		APIChecker apiChecker = new APIChecker();
		method.accept(apiChecker, null);
		return apiChecker.callsPerform || apiChecker.callsRestTemplate;
	}

	public static TestType getType(MethodDeclaration method, TestClassType testClassType) {
		if (callsAPI(method))
			return TestType.API;
		if (TestClassRules.isSpringBootTestClass(testClassType) 
				|| TestClassRules.isSpringRunnerClass(testClassType))
				return TestType.Spring;
		return TestType.Unit;
	}

	public static boolean isTest(MethodDeclaration method) {
		return method.getAnnotationByName("Test").isPresent();
	}

	public static boolean isIgnored(MethodDeclaration method) {
		return method.getAnnotationByName("Disabled").isPresent() || method.getAnnotationByName("Ignore").isPresent();
	}

	public static boolean hasExpected(MethodDeclaration method) {
		ExpectedJUnit4Checker expectedAnnotationFinder = new ExpectedJUnit4Checker();
		method.accept(expectedAnnotationFinder, null);
		ExpectedRuleJUnit4Checker expectedRuleFinder = new ExpectedRuleJUnit4Checker();
		method.accept(expectedRuleFinder, null);
		return expectedAnnotationFinder.hasExpectedAnnotation ||
				expectedRuleFinder.hasExpectedRule;
	}

}
