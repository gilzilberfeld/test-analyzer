package testanalyzer.parsing;

import com.github.javaparser.ast.body.MethodDeclaration;

import testanalyzer.parsing.rules.ExpectedJUnit4Rules;
import testanalyzer.parsing.rules.ExpectedRuleJUnit4Rules;

public class TestParser {

	public static boolean isTest(MethodDeclaration method) {
		return method.getAnnotationByName("Test").isPresent();
	}

	public static boolean isIgnored(MethodDeclaration method) {
		return method.getAnnotationByName("Disabled").isPresent() || method.getAnnotationByName("Ignore").isPresent();
	}

	public static boolean hasExpected(MethodDeclaration method) {
		ExpectedJUnit4Rules expectedAnnotationFinder = new ExpectedJUnit4Rules();
		method.accept(expectedAnnotationFinder, null);
		ExpectedRuleJUnit4Rules expectedRuleFinder = new ExpectedRuleJUnit4Rules();
		method.accept(expectedRuleFinder, null);
		return expectedAnnotationFinder.hasExpectedAnnotation ||
				expectedRuleFinder.hasExpectedRule;
	}

}
