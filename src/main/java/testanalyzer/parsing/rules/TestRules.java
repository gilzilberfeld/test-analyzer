package testanalyzer.parsing.rules;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import testanalyzer.model.TestClassInfo;
import testanalyzer.model.TestInfo;

public class TestRules extends VoidVisitorAdapter<Void> {

	
	public  TestClassInfo testClassInfo = new TestClassInfo();
	
	@Override
	public void visit(MethodDeclaration method, Void arg) {
		
		if (isTest(method)) {
			if (!isIgnored(method)) {
				TestInfo testInfo = testClassInfo.create();
				testInfo.testName = method.getName().toString();
				if (hasExpected(method)) {
					testInfo.assertCount = 1;
				}
				testInfo.assertCount += countAsserts(method);
				testClassInfo.incrementTests(); 
			}
		}
	}

	
	private int countAsserts(MethodDeclaration method) {
		AssertRules assertChecker = new AssertRules();
		method.accept(assertChecker, null);
		return assertChecker.count;
	}



	
	private boolean isTest(MethodDeclaration method) {
		return method.getAnnotationByName("Test").isPresent();
	}

	private boolean isIgnored(MethodDeclaration method) {
		return method.getAnnotationByName("Disabled").isPresent() || method.getAnnotationByName("Ignore").isPresent();
	}

	private boolean hasExpected(MethodDeclaration method) {
		ExpectedJUnit4Rules expectedAnnotationFinder = new ExpectedJUnit4Rules();
		method.accept(expectedAnnotationFinder, null);
		ExpectedRuleJUnit4Rules expectedRuleFinder = new ExpectedRuleJUnit4Rules();
		method.accept(expectedRuleFinder, null);
		return expectedAnnotationFinder.hasExpectedAnnotation ||
				expectedRuleFinder.hasExpectedRule;
	}

}
